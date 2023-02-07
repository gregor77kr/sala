package net.mwav.sala.subscription.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.product.repository.ProductRepository;
import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.entity.SubscriptionItem;
import net.mwav.sala.subscription.repository.SubscriptionItemRepository;
import net.mwav.sala.subscription.repository.SubscriptionRepository;
import net.mwav.sala.subscription.state.CreatedState;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	
	private final SubscriptionItemRepository subscriptionItemRepository;
	
	private final ProductRepository productRepository;

	public Subscription getSubscription(long subscriptionId) {
		return subscriptionRepository.findById(subscriptionId).orElseThrow(EntityNotFoundException::new);
	}

	// subscribe
	@Transactional
	public void subscribe(Subscription subscription) {
		subscription.changeState(new CreatedState());
		subscription = subscriptionRepository.save(subscription);
		log.debug(subscription.getItems().toString());
	}

	// 취소

	// 환불

}
