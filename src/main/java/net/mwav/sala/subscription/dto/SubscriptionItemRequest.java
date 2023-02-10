package net.mwav.sala.subscription.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.subscription.entity.SubscriptionItem;

@Value
public class SubscriptionItemRequest implements Serializable {

	private static final long serialVersionUID = -8531647817553454191L;

	private long productId;

	private double price;

	private int quantity;

	@JsonCreator
	private SubscriptionItemRequest(
			@JsonProperty long productId,
			@JsonProperty double price,
			@JsonProperty int quantity) {
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}

	public SubscriptionItem toEntity() {
		Product product = Product.builder().id(productId).build();

		return SubscriptionItem.builder()
				.product(product)
				.price(this.price)
				.quantity(this.quantity)
				.build();
	}
}
