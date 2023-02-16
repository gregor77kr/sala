package net.mwav.sala.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.entity.CustomerVerification;

public interface CustomerVerificationRepository extends CrudRepository<CustomerVerification, Long> {

	Optional<CustomerVerification> findByCustomerId(Long customerId);

	Optional<CustomerVerification> findByCustomer(Customer customer);
	
}