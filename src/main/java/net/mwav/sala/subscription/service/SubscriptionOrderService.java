package net.mwav.sala.subscription.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.subscription.entity.SubscriptionOrder;
import net.mwav.sala.subscription.repository.SubscriptionOrderRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionOrderService {

	private final SubscriptionOrderRepository subscriptionOrderRepository;

	public SubscriptionOrder createOrder(SubscriptionOrder subscriptionOrder) {
		subscriptionOrder.onCreate();
		return subscriptionOrderRepository.save(subscriptionOrder);
	}

}
