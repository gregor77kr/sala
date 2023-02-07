package net.mwav.sala.subscription.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.entity.SubscriptionItem;

public interface SubscriptionItemRepository extends JpaRepository<SubscriptionItem, Long> {
	
	List<SubscriptionItem> findAllBySubscription(Subscription subscription);
	
}
