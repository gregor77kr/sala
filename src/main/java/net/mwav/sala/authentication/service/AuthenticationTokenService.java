package net.mwav.sala.authentication.service;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.authentication.dto.TokenRequest;
import net.mwav.sala.authentication.dto.RefreshRequest;
import net.mwav.sala.authentication.dto.TokenResponse;
import net.mwav.sala.authentication.jwt.JwtTokenProvider;
import net.mwav.sala.security.service.SecurityService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationTokenService {

	private final JwtTokenProvider jwtTokenProvider;

	private final SecurityService securityService;

	public TokenResponse createToken(TokenRequest authenticationRequest) throws Exception {
		Authentication authentication = securityService.authenticate(authenticationRequest.getName(),
				authenticationRequest.getPassword());

		// create jwt access token
		String subject = authentication.getName();
		Collection<?> authorities = authentication.getAuthorities();

		log.debug("id : {}, authorities : {}", subject, authorities);

		String accessToken = jwtTokenProvider.createAccessToken(subject, authorities);
		String refreshtoken = jwtTokenProvider.createRefreshToken(subject);
		String cookieValue = jwtTokenProvider.createRefreshTokenInCookie(refreshtoken);

		// return access token
		return TokenResponse.builder()
				.accessToken(accessToken)
				.refreshToken(refreshtoken)
				.cookieValue(cookieValue)
				.build();
	}

	public TokenResponse refreshToken(RefreshRequest refreshRequest) {
		return TokenResponse.builder()
				.accessToken("")
				.build();
	}
}
