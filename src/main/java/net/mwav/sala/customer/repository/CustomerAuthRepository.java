package net.mwav.sala.customer.repository;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.mwav.sala.customer.dto.AuthenticationRequest;
import net.mwav.sala.customer.entity.CustomerAuth;

@Repository
public interface CustomerAuthRepository extends CrudRepository<CustomerAuth, Long> {

	Optional<CustomerAuth> findByCustomerId(long customerId);

	Optional<CustomerAuth> findByCustomerIdAndAuthenticationCode(long customerId, String authenticationCode);

	default CustomerAuth findOneByAuthentication(AuthenticationRequest authentication) {
		return this.findByCustomerIdAndAuthenticationCode(authentication.getCustomerId(), authentication
				.getAuthenticationCode()).orElseThrow(EntityNotFoundException::new);
	}
}