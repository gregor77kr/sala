package net.mwav.sala.authentication.service;

import java.util.Collection;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.authentication.dto.AuthenticationRequest;
import net.mwav.sala.authentication.dto.CustomerDetails;
import net.mwav.sala.authentication.dto.RefreshRequest;
import net.mwav.sala.authentication.dto.TokenResponse;
import net.mwav.sala.authentication.jwt.JwtTokenProvider;
import net.mwav.sala.common.util.HashUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

	private final JwtTokenProvider jwtTokenProvider;

	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	public TokenResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception {
		String actualPassword = authenticationRequest.getPassword();

		// create UsernamePasswordAuthenticationToken instance using AuthenticationRequest(id and password)
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest
				.getName(), actualPassword);

		// create Authentication instance using UsernamePasswordAuthenticationToken
		// during creating this instance, UserDetailSerivceImpl.loadUserByUsername is called
		// authenticate user
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		// compare password
		CustomerDetails customerDetails = (CustomerDetails) authentication.getPrincipal();
		String expectedPassword = customerDetails.getPassword();
		actualPassword = HashUtils.digest("SHA-256", actualPassword + customerDetails.getSalt());

		if (!expectedPassword.equals(actualPassword)) {
			throw new BadCredentialsException("아이디 혹은 비밀번호를 확인해주세요.");
		}

		// save Authentication in security context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// create jwt access token
		String subject = authentication.getName();
		log.debug("id : {}, authorities : {}", subject, authentication.getAuthorities());

		String accessToken = jwtTokenProvider.createAccessToken(subject, authentication.getAuthorities());
		String refreshtoken = jwtTokenProvider.createRefreshToken(subject);

		// return access token
		return TokenResponse.builder()
				.accessToken(accessToken)
				.refreshToken(refreshtoken)
				.build();
	}

	public String createAccessToken(String subject, Collection<? extends GrantedAuthority> authorities) {
		//		String claim = authorities
		//		.stream()
		//		.map(GrantedAuthority::getAuthority)
		//		.collect(Collectors.joining(","));
		return jwtTokenProvider.createAccessToken(subject, authorities);
	}

	public String createRefreshToken(String subject) {
		return jwtTokenProvider.createRefreshToken(subject);
	}

	public TokenResponse refresh(RefreshRequest refreshRequest) {
		return TokenResponse.builder()
				.accessToken("")
				.build();
	}
}
