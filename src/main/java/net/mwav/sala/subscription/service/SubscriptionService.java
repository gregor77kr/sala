package net.mwav.sala.subscription.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.order.entity.Order;
import net.mwav.sala.order.service.OrderService;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.product.service.ProductService;
import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.entity.SubscriptionItem;
import net.mwav.sala.subscription.repository.SubscriptionRepository;
import net.mwav.sala.subscription.state.CreatedSubscription;
import net.mwav.sala.transaction.entity.Transaction;
import net.mwav.sala.transaction.service.TransactionService;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	private final ProductService productService;

	private final OrderService orderService;

	private final TransactionService transactionService;

	// subscribe
	@Transactional
	public Subscription subscribe(Subscription subscription) {
		// items의 product id로 가격을 조회하여 bind
		List<SubscriptionItem> items = subscription.getItems();
		mapItemToProduct(items);

		subscription = createSubscription(subscription);
		Order order = createOrder(subscription);
		log.debug(order.toString());
		createTransaction(order);

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

	@Transactional
	public Subscription createSubscription(Subscription subscription) {
		subscription.changeState(new CreatedSubscription());
		subscription = subscriptionRepository.save(subscription);
		return subscription;
	}

	@Transactional
	private Order createOrder(Subscription subscription) {
		Order order = subscription.toOrder();
		order = orderService.createOrder(order);
		return order;
	}

	@Transactional
	private Transaction createTransaction(Order order) {
		Transaction transaction = order.toTransaction();
		transaction = transactionService.createTransaction(transaction);
		return transaction;
	}

	// 취소

	// 환불

}
