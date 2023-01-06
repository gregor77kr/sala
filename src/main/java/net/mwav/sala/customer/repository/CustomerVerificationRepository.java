package net.mwav.sala.customer.repository;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.mwav.sala.customer.dto.VerificationRequest;
import net.mwav.sala.customer.entity.CustomerVerification;

@Repository
public interface CustomerVerificationRepository extends CrudRepository<CustomerVerification, Long> {

	Optional<CustomerVerification> findByCustomerId(long customerId);

	Optional<CustomerVerification> findByCustomerIdAndVerificationCode(long customerId, String verificationCode);

	default CustomerVerification verify(VerificationRequest verification) {
		// TODO : EntityNotFoundException -> 인증 실패 exception으로 변환
		return this
				.findByCustomerIdAndVerificationCode(verification.getCustomerId(), verification.getVerificationCode())
				.orElseThrow(EntityNotFoundException::new);
	}
}