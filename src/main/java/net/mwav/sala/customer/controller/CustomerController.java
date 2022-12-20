package net.mwav.sala.customer.controller;

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
import net.mwav.sala.common.dto.StandardResponseBody;
import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.dto.SignUpResponse;
import net.mwav.sala.customer.service.CustomerService;

@RestController
@RequestMapping(value = "customer")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) throws NoSuchAlgorithmException {
		StandardResponseBody<SignUpResponse> response = StandardResponseBody
				.success(customerService.signUp(signUpRequest));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
