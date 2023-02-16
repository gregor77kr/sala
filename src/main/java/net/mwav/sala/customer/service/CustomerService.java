package net.mwav.sala.customer.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Transactional(rollbackFor = Exception.class)
	public Customer signUp(Customer customer) throws NoSuchAlgorithmException {
		boolean isSignedUp = customerRepository.findByNameOrEmail(customer.getName(), customer.getEmail())
				.isPresent();

		if (isSignedUp) {
			throw new DataIntegrityViolationException("이미 가입한 사용자입니다.");
		}

		customer.digestPassword();
		customer = customerRepository.save(customer);

		return customer;
	}

	public Optional<Customer> findCustomer(Long customerId) {
		return customerRepository.findById(customerId);
	}

}
