package net.mwav.sala.subscription.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.subscription.entity.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

}
