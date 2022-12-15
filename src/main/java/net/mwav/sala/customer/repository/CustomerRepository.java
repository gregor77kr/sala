package net.mwav.sala.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.mwav.sala.customer.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Optional<Customer> findOneByName(String name);

	Optional<Customer> findOneByEmail(String email);

	Optional<Customer> findOneByNameOrEmail(String name, String email);

}
