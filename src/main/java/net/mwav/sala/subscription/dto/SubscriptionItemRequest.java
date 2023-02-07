package net.mwav.sala.subscription.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
import net.mwav.sala.common.constant.Currency;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.subscription.entity.SubscriptionItem;

@Value
public class SubscriptionItemRequest implements Serializable {

	private static final long serialVersionUID = -8531647817553454191L;

	private long productId;

	private String currency;

	private double price;

	private int quantity;

	@JsonCreator
	private SubscriptionItemRequest(
			@JsonProperty long productId,
			@JsonProperty String currency,
			@JsonProperty double price,
			@JsonProperty int quantity) {
		this.productId = productId;
		this.currency = currency;
		this.price = price;
		this.quantity = quantity;
	}

	public SubscriptionItem toEntity() {
		Product product = Product.builder().id(productId).build();

		return SubscriptionItem.builder()
				.product(product)
				.currency(Currency.valueOf(currency))
				.price(price)
				.quantity(quantity)
				.build();
	}
}
