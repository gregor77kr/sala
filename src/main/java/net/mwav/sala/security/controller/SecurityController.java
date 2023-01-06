package net.mwav.sala.security.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.common.dto.StandardResponseBody;
import net.mwav.sala.security.dto.AuthenticationRequest;
import net.mwav.sala.security.service.JwtProvider;

@RestController
@RequestMapping(value = "customers")
@RequiredArgsConstructor
@Slf4j
public class SecurityController {

	private final JwtProvider jwtProvider;

	@PostMapping(value = "/authentication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws NoSuchAlgorithmException {
		StandardResponseBody<?> response = StandardResponseBody
				.success(jwtProvider.authenticate(authenticationRequest));

		log.debug(authenticationRequest.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
