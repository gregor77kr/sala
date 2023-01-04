package net.mwav.sala.customer.repository;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	default Customer findOneById(long id) {
		return this.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	Optional<Customer> findOneByNameOrEmail(String name, String email);

	default <T extends SignUpRequest> boolean isSignedUp(T customer) {
		return this.findOneByNameOrEmail(customer.getName(), customer.getEmail()).isPresent();
	}
}
