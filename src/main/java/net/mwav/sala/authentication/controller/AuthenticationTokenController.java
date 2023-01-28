package net.mwav.sala.authentication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.authentication.dto.RefreshRequest;
import net.mwav.sala.authentication.dto.TokenRequest;
import net.mwav.sala.authentication.dto.TokenResponse;
import net.mwav.sala.authentication.service.AuthenticationTokenService;
import net.mwav.sala.common.dto.StandardResponseBody;

@RestController
@RequestMapping(value = "/api/authentication")
@RequiredArgsConstructor
public class AuthenticationTokenController {

	private final AuthenticationTokenService authenticationTokenService;

	// authenticate and get access token
	@PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createToken(@Valid @RequestBody TokenRequest authenticationRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TokenResponse tokenResponse = authenticationTokenService.createToken(authenticationRequest);

		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success(tokenResponse);
		return ResponseEntity.status(HttpStatus.OK)
			.header(HttpHeaders.SET_COOKIE, tokenResponse.getCookieString())
			.body(standardResponseBody);
	}

	// refresh token
	@PutMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reissue(@Valid @RequestBody RefreshRequest refreshRequest, HttpServletRequest request) throws Exception {
		String refreshToken = WebUtils.getCookie(request, "SFDD").getValue();

		StandardResponseBody<?> standardResponseBody = StandardResponseBody
			.success(authenticationTokenService.reissue(refreshRequest.getAccessToken(), refreshToken));

		return ResponseEntity.status(HttpStatus.OK).body(standardResponseBody);
	}

}
