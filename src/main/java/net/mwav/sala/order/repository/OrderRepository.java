package net.mwav.sala.order.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwav.sala.order.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
