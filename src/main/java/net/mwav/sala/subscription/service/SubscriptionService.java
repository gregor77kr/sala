package net.mwav.sala.subscription.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.product.service.ProductService;
import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.entity.SubscriptionItem;
import net.mwav.sala.subscription.entity.SubscriptionOrder;
import net.mwav.sala.subscription.repository.SubscriptionRepository;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	private final ProductService productService;

	private final SubscriptionOrderService subscriptionOrderService;

	// subscribe
	@Transactional(rollbackFor = Exception.class)
	public Subscription subscribe(Subscription subscription) {
		// items의 product id로 가격을 조회하여 bind
		List<SubscriptionItem> items = subscription.getItems();
		mapItemToProduct(items);

		subscription = createSubscription(subscription);
		createOrder(subscription);

		return subscription;
	}

	@Transactional(rollbackFor = Exception.class)
	private void mapItemToProduct(List<SubscriptionItem> items) {
		// items의 product id로 가격을 조회하여 bind
		List<Long> ids = items.stream().map(i -> i.getProduct().getId()).collect(Collectors.toList());
		List<Product> products = productService.findProducts(ids);

		items.stream().forEach(i -> {
			Product product = products.stream()
					.filter(p -> p.getId() == i.getProduct().getId())
					.findFirst()
					.orElseThrow(EntityNotFoundException::new);

			i.setProduct(product);
		});
	}

	@Transactional(rollbackFor = Exception.class)
	public Subscription createSubscription(Subscription subscription) {
		subscription.onCreate();
		subscription = subscriptionRepository.save(subscription);
		return subscription;
	}

	@Transactional(rollbackFor = Exception.class)
	private SubscriptionOrder createOrder(Subscription subscription) {
		SubscriptionOrder subscriptionOrder = subscription.toOrder();
		subscriptionOrder = subscriptionOrderService.createOrder(subscriptionOrder);
		return subscriptionOrder;
	}

	// 취소

	// 환불

}
