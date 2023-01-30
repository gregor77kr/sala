package net.mwav.sala.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.customer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Optional<Customer> findByName(String name);

	Optional<Customer> findByNameOrEmail(String name, String email);

}
