package net.mwav.sala.billing.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.billing.entity.Billing;

public interface BillingRepository extends CrudRepository<Billing, Long> {

}
