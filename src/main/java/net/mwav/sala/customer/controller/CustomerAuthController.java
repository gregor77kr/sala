package net.mwav.sala.customer.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.common.dto.StandardResponseBody;
import net.mwav.sala.common.exception.ExpiryException;
import net.mwav.sala.customer.dto.AuthenticationRequest;
import net.mwav.sala.customer.service.CustomerAuthService;

@RestController
@RequestMapping(value = "customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerAuthController {

	private final CustomerAuthService customerAuthService;

	@PostMapping(value = "/authentication/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendAuthentication(@PathVariable("customerId") long customerId) {
		log.debug("customerId : " + customerId);
		customerAuthService.sendAuthentication(customerId);

		StandardResponseBody<?> response = StandardResponseBody.success();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping(value = "/authentication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws ExpiryException {
		customerAuthService.authenticate(authenticationRequest);

		StandardResponseBody<?> response = StandardResponseBody.success();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
