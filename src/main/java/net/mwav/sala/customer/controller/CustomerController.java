package net.mwav.sala.customer.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.customer.dto.ProfileResponse;
import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.dto.SignUpResponse;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.service.CustomerService;
import net.mwav.sala.global.dto.StandardResponseBody;

@RestController
@RequestMapping(value = "/api/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) throws NoSuchAlgorithmException {
		Customer customer = signUpRequest.toEntity();
		SignUpResponse signUpResponse = SignUpResponse.from(customerService.signUp(customer));
		StandardResponseBody<SignUpResponse> standardResponseBody = StandardResponseBody
				.success(signUpResponse);

		return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
	}

	@GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findCustomer(@PathVariable("customerId") long customerId) {
		Customer customer = customerService.findCustomer(customerId);
		ProfileResponse profileResponse = ProfileResponse.from(customer);
		StandardResponseBody<ProfileResponse> standardResponseBody = StandardResponseBody
				.success(profileResponse);

		return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
	}

}
