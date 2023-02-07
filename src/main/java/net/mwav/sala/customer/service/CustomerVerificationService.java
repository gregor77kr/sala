package net.mwav.sala.customer.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.common.exception.ExpiryException;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.entity.CustomerVerification;
import net.mwav.sala.customer.repository.CustomerRepository;
import net.mwav.sala.customer.repository.CustomerVerificationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerVerificationService {

	private final CustomerRepository customerRepository;

	private final CustomerVerificationRepository customerVerificationRepository;

	public void sendVerification(long customerId) {
		CustomerVerification customerVerification = setVerification(customerId);
		sendEmail(customerVerification);
	}

	@Transactional(rollbackFor = Exception.class)
	private CustomerVerification setVerification(long customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);

		Optional<CustomerVerification> optionalCustomerVerification = customerVerificationRepository
				.findByCustomerId(customer.getId());
		CustomerVerification customerVerification;

		if (optionalCustomerVerification.isPresent()) {
			customerVerification = optionalCustomerVerification.get();
			customerVerification.setAuthenticationRequest();
		} else {
			customerVerification = customerVerificationRepository.save(CustomerVerification.generate(customer));
		}

		return customerVerification;
	}

	private void sendEmail(CustomerVerification customerVerification) {
		// TODO : 메일발송
		log.debug(customerVerification.getCustomer().getEmail());
		log.debug(customerVerification.getVerificationCode());
	}

	@Transactional(rollbackFor = Exception.class)
	public void verify(CustomerVerification customerVerification) throws ExpiryException {
		CustomerVerification foundCustomerVerification = customerVerificationRepository
				.findByCustomer(customerVerification.getCustomer())
				.orElseThrow(EntityNotFoundException::new);

		foundCustomerVerification.verify(customerVerification.getVerificationCode());
	}
}
