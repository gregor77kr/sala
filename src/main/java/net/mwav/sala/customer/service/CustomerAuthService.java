package net.mwav.sala.customer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.common.exception.ExpiryException;
import net.mwav.sala.customer.dto.AuthenticationRequest;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.entity.CustomerAuth;
import net.mwav.sala.customer.repository.CustomerAuthRepository;
import net.mwav.sala.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerAuthService {

	private final CustomerRepository customerRepository;

	private final CustomerAuthRepository customerAuthRepository;

	public void sendAuthentication(long customerId) {
		CustomerAuth customerAuth = setAuthentication(customerId);

		// TODO : 메일발송
		log.debug(customerAuth.getCustomer().getEmail());
		log.debug(customerAuth.getAuthenticationCode());
	}

	@Transactional(rollbackFor = Exception.class)
	private CustomerAuth setAuthentication(long customerId) {
		Customer customer = customerRepository.findOneById(customerId);

		Optional<CustomerAuth> optionalCustomerAuth = customerAuthRepository.findByCustomerId(customer.getId());
		CustomerAuth customerAuth;

		if (optionalCustomerAuth.isPresent()) {
			customerAuth = optionalCustomerAuth.get();
			customerAuth.setAuthenticationRequest();
		} else {
			customerAuth = customerAuthRepository.save(CustomerAuth.create(customer));
		}

		return customerAuth;
	}

	@Transactional(rollbackFor = Exception.class)
	public void authenticate(AuthenticationRequest authenticationRequest) throws ExpiryException {
		CustomerAuth customerAuth = customerAuthRepository.findOneByAuthentication(authenticationRequest);

		customerAuth.ifValid(c -> {
			c.authenticate();
		}).orElseThrow(ExpiryException::new);
	}
}
