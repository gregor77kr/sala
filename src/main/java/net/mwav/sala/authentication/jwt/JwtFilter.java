package net.mwav.sala.authentication.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.security.dto.Authority;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	// 인증에서 제외할 url
	private static final List<String> EXCLUDE_URL = Arrays
		.asList("/api/customers", "/api/authentication");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String accessToken = jwtTokenProvider.getAccessTokenInHeader(request);

		if (StringUtils.hasText(accessToken) && jwtTokenProvider.validateToken(accessToken)) {
			Authentication authentication = getAuthentication(accessToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
	}

	@SuppressWarnings("unchecked")
	public Authentication getAuthentication(String token) {
		String subject = jwtTokenProvider.getSubject(token);
		List<Map<String, ?>> data = (List<Map<String, ?>>) jwtTokenProvider.getData(token);
		List<Authority> authorities = Authority.map(data);

		log.debug("subject : {}, authorities : {}", subject, authorities);

		User principal = new User(subject, token, authorities);
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

}
