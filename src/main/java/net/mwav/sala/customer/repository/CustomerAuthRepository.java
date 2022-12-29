package net.mwav.sala.customer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.mwav.sala.customer.entity.CustomerAuth;

@Repository
public interface CustomerAuthRepository extends CrudRepository<CustomerAuth, Long> {
	
	Optional<CustomerAuth> findOneByCustomerId(long customerId);

	Optional<CustomerAuth> findOneByCustomerIdAndAuthenticationCode(long customerId, String authenticationCode);
}
