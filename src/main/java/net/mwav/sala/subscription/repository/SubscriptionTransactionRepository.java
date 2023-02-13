package net.mwav.sala.subscription.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.subscription.entity.SubscriptionTransaction;

public interface SubscriptionTransactionRepository extends CrudRepository<SubscriptionTransaction, Long> {
    
}
