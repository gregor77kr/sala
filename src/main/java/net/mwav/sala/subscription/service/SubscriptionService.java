package net.mwav.sala.subscription.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.product.service.ProductService;
import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.entity.SubscriptionItem;
import net.mwav.sala.subscription.repository.SubscriptionRepository;
import net.mwav.sala.subscription.state.CreatedState;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	private final ProductService productService;
	//private final ProductRepository productRepository;

	public Subscription getSubscription(long subscriptionId) {
		Subscription subscription = subscriptionRepository.findById(subscriptionId)
				.orElseThrow(EntityNotFoundException::new);
		log.debug(subscription.toString());
		return subscription;
	}

	// subscribe
	@Transactional
	public Subscription subscribe(Subscription subscription) {
		// items의 product id로 가격을 조회하여 bind
		List<SubscriptionItem> items = subscription.getItems();
		items = productService.getProductItems(items);
		
		subscription.changeState(new CreatedState());
		subscription = subscriptionRepository.save(subscription);
		return subscription;
	}

	// 취소

	// 환불

}
