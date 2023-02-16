package net.mwav.sala.authentication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.authentication.controller.dto.RefreshRequest;
import net.mwav.sala.authentication.controller.dto.TokenRequest;
import net.mwav.sala.authentication.controller.dto.TokenResponse;
import net.mwav.sala.authentication.entity.CustomerToken;
import net.mwav.sala.authentication.jwt.JwtWebResolver;
import net.mwav.sala.authentication.service.AuthenticationTokenService;
import net.mwav.sala.global.model.StandardResponseBody;

@RestController
@RequiredArgsConstructor
public class AuthenticationTokenController {

	private final AuthenticationTokenService authenticationTokenService;

	private final JwtWebResolver jwtWebResolver;

	// authenticate and get access token
	@PostMapping(value = "/api/authentication/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createToken(@Valid @RequestBody TokenRequest authenticationRequest) throws Exception {
		CustomerToken customerToken = authenticationTokenService.createToken(authenticationRequest.getName(),
				authenticationRequest.getPassword());
		TokenResponse tokenResponse = TokenResponse.from(customerToken);
		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success(tokenResponse);

		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.SET_COOKIE, jwtWebResolver.createRefreshCookie(tokenResponse.getRefreshToken()))
				.body(standardResponseBody);
	}

	// refresh token
	@PutMapping(value = "/api/authentication/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reissue(@Valid @RequestBody RefreshRequest refreshRequest, HttpServletRequest request)
			throws Exception {
		String refreshToken = jwtWebResolver.getRefreshTokenInCookie(request);
		CustomerToken customerToken = authenticationTokenService.reissue(refreshToken);
		TokenResponse tokenResponse = TokenResponse.from(customerToken);
		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success(tokenResponse);

		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.SET_COOKIE, jwtWebResolver.createRefreshCookie(tokenResponse.getRefreshToken()))
				.body(standardResponseBody);
	}

}
