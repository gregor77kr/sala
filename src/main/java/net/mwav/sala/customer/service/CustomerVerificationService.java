package net.mwav.sala.customer.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.common.exception.ExpiryException;
import net.mwav.sala.customer.dto.VerificationRequest;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.entity.CustomerVerification;
import net.mwav.sala.customer.repository.CustomerVerificationRepository;
import net.mwav.sala.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerVerificationService {

	private final CustomerRepository customerRepository;

	private final CustomerVerificationRepository customerVerificationRepository;

	public void sendVerification(long customerId) {
		CustomerVerification customerAuth = setVerification(customerId);

		// TODO : 메일발송
		log.debug(customerAuth.getCustomer().getEmail());
		log.debug(customerAuth.getVerificationCode());
	}

	@Transactional(rollbackFor = Exception.class)
	private CustomerVerification setVerification(long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);

		Optional<CustomerVerification> optionalCustomerAuth = customerVerificationRepository
				.findByCustomerId(customer.getId());
		CustomerVerification customerAuth;

		if (optionalCustomerAuth.isPresent()) {
			customerAuth = optionalCustomerAuth.get();
			customerAuth.setAuthenticationRequest();
		} else {
			customerAuth = customerVerificationRepository.save(CustomerVerification.create(customer));
		}

		return customerAuth;
	}

	@Transactional(rollbackFor = Exception.class)
	public void verify(VerificationRequest verification) throws ExpiryException {
		CustomerVerification customerVerification = customerVerificationRepository
				.findByCustomerId(verification.getCustomerId())
				.orElseThrow(EntityNotFoundException::new);

		customerVerification.verify(verification.getVerificationCode());
	}
}
