package net.mwav.sala.order.state;

import net.mwav.sala.order.entity.Order;

public interface OrderState {

	public void change(Order order);
	
}
