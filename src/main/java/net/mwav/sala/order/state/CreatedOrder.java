package net.mwav.sala.order.state;

import net.mwav.sala.global.constant.OrderStatus;
import net.mwav.sala.order.entity.Order;

public class CreatedOrder implements OrderState {

	@Override
	public void change(Order order) {
		order.generateNo();
		order.setOrderStatus(OrderStatus.CREATED);
		order.calculatePeriod();
	}

}
