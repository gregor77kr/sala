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

import lombok.RequiredArgsConstructor;
import net.mwav.sala.authentication.dto.AuthenticationRequest;
import net.mwav.sala.authentication.dto.RefreshRequest;
import net.mwav.sala.authentication.dto.TokenResponse;
import net.mwav.sala.authentication.service.AuthenticationService;
import net.mwav.sala.common.dto.StandardResponseBody;

@RestController
@RequestMapping(value = "/api/customers")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	// authenticate and get access token
	@PostMapping(value = "/authentication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {

		TokenResponse tokenResponse = authenticationService.authenticate(authenticationRequest);

		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success(tokenResponse);
		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.SET_COOKIE, tokenResponse.getCookieValue())
				.body(standardResponseBody);
	}

	// refresh token
	@PutMapping(value = "/authentication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> refresh(@Valid @RequestBody RefreshRequest refreshRequest) throws Exception {
		StandardResponseBody<?> standardResponseBody = StandardResponseBody
				.success(authenticationService.refresh(refreshRequest));

		return ResponseEntity.status(HttpStatus.OK).body(standardResponseBody);
	}

}
