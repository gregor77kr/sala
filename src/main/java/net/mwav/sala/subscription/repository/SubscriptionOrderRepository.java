package net.mwav.sala.subscription.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.subscription.entity.SubscriptionOrder;

public interface SubscriptionOrderRepository extends CrudRepository<SubscriptionOrder, Long> {

}
