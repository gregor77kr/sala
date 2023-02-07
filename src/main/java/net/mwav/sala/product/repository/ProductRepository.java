package net.mwav.sala.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.mwav.sala.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByIdAndIsActive(long id, boolean isActive);
	
}
