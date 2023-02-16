package net.mwav.sala.customer.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.customer.controller.dto.VerificationRequest;
import net.mwav.sala.customer.entity.CustomerVerification;
import net.mwav.sala.customer.service.CustomerVerificationService;
import net.mwav.sala.global.exception.ExpiryException;
import net.mwav.sala.global.model.StandardResponseBody;
import net.mwav.sala.security.service.SecurityResolver;

@RestController
@RequiredArgsConstructor
public class CustomerVerificationController {

	private final CustomerVerificationService customerVerificationService;

	@PostMapping(value = "/api/customers/verification/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendVerification(@PathVariable("customerId") Long customerId) {
		SecurityResolver.authorize(customerId);
		customerVerificationService.sendVerification(customerId);
		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success();

		return ResponseEntity.status(HttpStatus.OK).body(standardResponseBody);
	}

	@PutMapping(value = "/api/customers/verification/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticate(@PathVariable("customerId") Long customerId,
			@Valid @RequestBody VerificationRequest verificationRequest) throws ExpiryException {

		if (customerId != verificationRequest.getCustomerId()) {
			throw new IllegalArgumentException();
		}

		SecurityResolver.authorize(customerId);
		CustomerVerification customerVerification = verificationRequest.toEntity();
		customerVerificationService.verify(customerVerification);

		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success();
		return ResponseEntity.status(HttpStatus.OK).body(standardResponseBody);
	}
}
