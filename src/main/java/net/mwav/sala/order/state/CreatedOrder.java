package net.mwav.sala.order.state;

import java.time.LocalDate;
import java.time.LocalDateTime;

import net.mwav.sala.global.constant.OrderStatus;
import net.mwav.sala.global.constant.PaymentPeriod;
import net.mwav.sala.order.entity.Order;

public class CreatedOrder implements OrderState {

	@Override
	public void change(Order order) {

		LocalDate stardDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusMonths((order.getPaymentPeriod() == PaymentPeriod.MONTHLY) ? 1 : 12);

		order.generateNo();
		order.setOrderStatus(OrderStatus.CREATED);
		order.setOrderDate(LocalDateTime.now());
		order.setPeriodStartDate(stardDate);
		order.setPeriodEndDate(endDate);

	}

}
