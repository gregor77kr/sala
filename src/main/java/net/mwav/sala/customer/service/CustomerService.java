package net.mwav.sala.customer.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.dto.SignUpResponse;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.repository.CustomerRepository;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public SignUpResponse signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException {
		log.debug(signUpRequest.toString());

		Optional<Customer> signedCustomer = customerRepository
				.findOneByNameOrEmail(signUpRequest.getName(), signUpRequest.getEmail());

		if (signedCustomer.isPresent()) {
			throw new DataIntegrityViolationException("이미 가입한 사용자입니다.");
		}

		Customer signningCustomer = signUpRequest.toCustomer();
		signningCustomer.digestPassword();
		customerRepository.save(signningCustomer);

		return SignUpResponse.builder().build();
	}
}
