package net.mwav.sala.security.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import net.mwav.sala.security.dto.AuthenticationRequest;
import net.mwav.sala.security.dto.CustomerDetails;

@RequiredArgsConstructor
@Component
public class JwtProvider {

	// TODO : key 설정 파일로 이동 및 환경 분리
	private String SECRET_KEY = "mwav";

	private final long TOKEN_VALIDITY_TERM = 60 * 60 * 1000L;

	//private final String ROLE_KEY = "role";

	private final String HEADER_TOKEN_KEY = "X-AUTH-TOKEN";

	private final UserDetailServiceImpl userDetailServiceImpl;

	@PostConstruct
	private void init() {
		SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	public String createToken(long customerId) {
		Claims claims = Jwts.claims().setSubject(String.valueOf(customerId));

		Date now = new Date();

		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + TOKEN_VALIDITY_TERM))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
				.compact();
	}

	public String getCustomerId(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public String resolveToken(HttpServletRequest request) {
		return request.getHeader(HEADER_TOKEN_KEY);
	}

	public boolean isValidToken(String token) {
		Jws<Claims> claimsJws = Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
				.build()
				.parseClaimsJws(token);

		return !claimsJws.getBody().getExpiration().before(new Date());
	}

	public Authentication getAuthentication(String token) {
		String customerId = getCustomerId(token);
		CustomerDetails userDetails = userDetailServiceImpl.loadUserByUsername(customerId);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String authenticate(AuthenticationRequest authenticationRequest) throws NoSuchAlgorithmException {
		CustomerDetails userDetails = userDetailServiceImpl.authenticate(authenticationRequest);
		return createToken(userDetails.getCustomerId());
	}

}
