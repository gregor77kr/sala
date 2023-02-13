package net.mwav.sala.order.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.order.entity.Order;
import net.mwav.sala.order.repository.OrderRepository;
import net.mwav.sala.order.state.CreatedOrder;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	public Order createOrder(Order order) {
		order.changeState(new CreatedOrder());
		return orderRepository.save(order);
	}

}
