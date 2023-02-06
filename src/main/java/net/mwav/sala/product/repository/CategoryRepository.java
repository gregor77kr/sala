package net.mwav.sala.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.product.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAllByIsActive(boolean isActive);

}
