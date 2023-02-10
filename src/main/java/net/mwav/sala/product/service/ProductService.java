package net.mwav.sala.product.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.product.repository.ProductRepository;
import net.mwav.sala.subscription.entity.SubscriptionItem;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<SubscriptionItem> getProductItems(List<SubscriptionItem> items) {
		List<SubscriptionItem> copies = items.stream().collect(Collectors.toList());
		
		List<Product> products = productRepository
				.findAllById(copies.stream().map(i -> i.getProduct().getId()).collect(Collectors.toList()));
		copies.stream().forEach(i -> {
			Product product = products.stream()
					.filter(p -> p.getId() == i.getProduct().getId())
					.findFirst()
					.orElseThrow(EntityNotFoundException::new);

			i.setProduct(product);
		});
		
		return copies;
	}

}
