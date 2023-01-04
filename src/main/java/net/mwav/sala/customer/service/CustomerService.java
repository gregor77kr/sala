package net.mwav.sala.customer.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.dto.SignUpResponse;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Transactional(rollbackFor = Exception.class)
	public SignUpResponse signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException {		
		if (customerRepository.isSignedUp(signUpRequest)) {
			throw new DataIntegrityViolationException("이미 가입한 사용자입니다.");
		}

		Customer customer = signUpRequest.toCustomer();
		customer.digestPassword();
		customerRepository.save(customer);

		SignUpResponse response = SignUpResponse.map(customer);
		return response;
	}

}
