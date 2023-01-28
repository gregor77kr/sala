package net.mwav.sala.authentication.jwt;

import java.security.Key;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token-validity}")
	private long accessTokenValidity;

	@Value("${jwt.access-token-name}")
	private String accessTokenName;

	@Value("${jwt.refresh-token-validity}")
	private long refreshTokenValidity;

	@Value("${jwt.refresh-token-name}")
	private String refreshTokenName;

	@Value("${jwt.refresh-token-url}")
	private String refreshTokenUrl;

	@Value("${jwt.domain}")
	private String domain;

	private static final String CLAIM_KEY = "data";

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret));
		log.debug(key.toString());
	}

	public <T> String createAccessToken(String subject, T data) {
		return this.createToken(subject, this.accessTokenValidity, data);
	}

	public String createRefreshToken(String subject) {
		return this.createToken(subject, this.refreshTokenValidity, null);
	}

	private <T> String createToken(String subject, long validity, T data) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + validity);

		Claims claims = Jwts.claims()
			.setSubject(subject)
			.setIssuedAt(now)
			.setExpiration(expiration);
		claims.put(CLAIM_KEY, data);

		return Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setClaims(claims)
			.signWith(key, SignatureAlgorithm.HS512)
			.compact();
	}

	public String createRefreshCookie(String token) {
		return createCookieString(this.refreshTokenName, token, this.refreshTokenValidity / 1000);
	}

	private String createCookieString(String name, String token, long validity) {
		ResponseCookie responseCookie = ResponseCookie.from(name, token)
			.httpOnly(true)
			.sameSite("Lax")
			.domain(domain)
			.secure(false)
			.maxAge(validity)
			.build();
		return responseCookie.toString();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.debug("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.debug("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.debug("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.debug("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

	public String getSubject(String token) {
		return getClaims(token).getSubject();
	}

	public Object getData(String token) {
		return getClaims(token).get(CLAIM_KEY);
	}

	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public String getAccessTokenInHeader(HttpServletRequest request) {
		String bearerToken = request.getHeader(accessTokenName);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
