package net.mwav.sala.authentication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.authentication.entity.CustomerToken;

public interface CustomerTokenRepository extends CrudRepository<CustomerToken, Long> {

	Optional<CustomerToken> findByCustomerIdAndRefreshToken(long customerId, String refreshToken);

}
