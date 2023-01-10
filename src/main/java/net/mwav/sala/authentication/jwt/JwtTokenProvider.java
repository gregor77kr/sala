package net.mwav.sala.authentication.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

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

	private static final String AUTHORITIES_KEY = "Authorization";

	private final String secret;

	private final long accessTokenValidity;

	private final long refreshTokenValidity;

	private Key key;

	public JwtTokenProvider(
			@Value("${jwt.secret}") String secret,
			@Value("${jwt.access-token-validity}") long accessTokenValidity,
			@Value("${jwt.refresh-token-validity}") long refreshTokenValidity) {
		this.secret = secret;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret));
	}

	public String createToken(String subject, Collection<? extends GrantedAuthority> authorities) {
		String claim = authorities
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		Date now = new Date();
		Date validity = new Date(now.getTime() + this.accessTokenValidity);

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setSubject(subject)
				.claim(AUTHORITIES_KEY, claim)
				.signWith(key, SignatureAlgorithm.HS512)
				.setIssuedAt(now)
				.setExpiration(validity)
				.compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		User principal = new User(claims.getSubject(), "", authorities);

		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
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

}
