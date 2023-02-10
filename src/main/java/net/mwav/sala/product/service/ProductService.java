package net.mwav.sala.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<Product> findProducts(List<Long> items) {
		List<Product> products = productRepository.findAllById(items);
		return products;
	}

}
