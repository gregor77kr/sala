package net.mwav.sala.customer.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.dto.SignUpResponse;
import net.mwav.sala.customer.service.CustomerService;

@RestController(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/sign-up")
	@ResponseBody
	public ResponseEntity<SignUpResponse> signUp(@Valid @ModelAttribute SignUpRequest signUpRequest) throws NoSuchAlgorithmException {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.signUp(signUpRequest));
	}
}
