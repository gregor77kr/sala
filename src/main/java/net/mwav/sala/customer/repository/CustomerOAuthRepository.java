package net.mwav.sala.customer.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.customer.entity.CustomerOAuth;

public interface CustomerOAuthRepository extends CrudRepository<CustomerOAuth, Long> {
	
}
