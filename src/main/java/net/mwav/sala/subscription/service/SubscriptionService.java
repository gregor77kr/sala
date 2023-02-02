package net.mwav.sala.subscription.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.repository.SubscriptionRepository;
import net.mwav.sala.subscription.state.CreatedState;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	// subscribe
	public Object subscribe(Subscription subscription) {
		log.debug(subscription.toString());
		subscription = createSubscription(subscription);

		return null;
	}

	// create subscription routine
	private Subscription createSubscription(Subscription subscription) {
		subscription.changeStatus(new CreatedState());

		return subscriptionRepository.save(subscription);
	}

	// 취소

	// 환불

}
