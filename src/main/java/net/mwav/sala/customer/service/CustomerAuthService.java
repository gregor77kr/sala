package net.mwav.sala.customer.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.customer.dto.AuthenticationRequest;
import net.mwav.sala.customer.entity.CustomerAuth;
import net.mwav.sala.customer.repository.CustomerAuthRepository;
import net.mwav.sala.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerAuthService {

	private final CustomerRepository customerRepository;

	private final CustomerAuthRepository customerAuthRepository;

	@Transactional
	public void sendAuthentication(long customerId) {
		customerRepository.findById(customerId).orElseThrow(NoSuchElementException::new);

		Optional<CustomerAuth> customerAuth = customerAuthRepository.findOneByCustomerId(customerId);

		if (customerAuth.isPresent()) {
			customerAuth.get().setAuthenticationRequest();
		} else {
			CustomerAuth createdCustomerAuth = CustomerAuth.create(customerId);
			customerAuthRepository.save(createdCustomerAuth);
		}

		// TODO : 메일발송
	}

	public void authenticate(AuthenticationRequest authenticationRequest) {
		customerAuthRepository
			.findOneByCustomerIdAndAuthenticationCode(authenticationRequest.getCustomerId(), authenticationRequest
				.getAuthenticationCode())
			.orElseThrow(NoSuchElementException::new);
	}
}
