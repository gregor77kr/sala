package net.mwav.sala.customer.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.customer.dto.ProfileResponse;
import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.dto.SignUpResponse;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	public void signIn() {

	}

	@Transactional(rollbackFor = Exception.class)
	public SignUpResponse signUp(SignUpRequest signUpRequest) throws NoSuchAlgorithmException {
		if (customerRepository.isSignedUp(signUpRequest)) {
			throw new DataIntegrityViolationException("이미 가입한 사용자입니다.");
		}

		Customer customer = signUpRequest.toEntity();
		customer.digestPassword();
		customerRepository.save(customer);

		SignUpResponse response = SignUpResponse.from(customer);
		return response;
	}

	public ProfileResponse getProfile(long customerId) {
		Customer customer = customerRepository.findOneById(customerId);
		ProfileResponse response = ProfileResponse.from(customer);
		return response;
	}

}
