package net.mwav.sala.subscription.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
import net.mwav.sala.common.constant.Currency;
import net.mwav.sala.subscription.entity.SubscriptionItem;

@Value
public class SubscriptionItemRequest implements Serializable {

    private String currency;

    private double price;

    private int quantity;

    @JsonCreator
    private SubscriptionItemRequest(@JsonProperty String currency,
            @JsonProperty double price,
            @JsonProperty int quantity) {
        this.currency = currency;
        this.price = price;
        this.quantity = quantity;
    }

    public SubscriptionItem toEntity() {
        return SubscriptionItem.builder(null)
                .currency(Currency.valueOf(currency))
                .price(price)
                .quantity(quantity)
                .build();
    }
}
