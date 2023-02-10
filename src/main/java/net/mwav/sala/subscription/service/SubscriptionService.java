package net.mwav.sala.subscription.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.product.service.ProductService;
import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.entity.SubscriptionItem;
import net.mwav.sala.subscription.repository.SubscriptionRepository;
import net.mwav.sala.subscription.state.CreatedState;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	private final ProductService productService;

	// subscribe
	@Transactional
	public Subscription subscribe(Subscription subscription) {
		// items의 product id로 가격을 조회하여 bind
		List<SubscriptionItem> items = subscription.getItems();
		mapItemToProduct(items);

		subscription.changeState(new CreatedState());
		subscription = subscriptionRepository.save(subscription);
		return subscription;
	}

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

	// 취소

	// 환불

}
