package net.mwav.sala.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.customer.entity.CustomerVerification;

public interface CustomerVerificationRepository extends CrudRepository<CustomerVerification, Long> {

	Optional<CustomerVerification> findByCustomerId(long customerId);

}