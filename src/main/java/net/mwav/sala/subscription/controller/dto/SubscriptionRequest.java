package net.mwav.sala.subscription.controller.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.product.entity.constant.Currency;
import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.entity.SubscriptionItem;
import net.mwav.sala.subscription.entity.constant.PaymentMethod;
import net.mwav.sala.subscription.entity.constant.PaymentPeriod;

@Value
public class SubscriptionRequest implements Serializable {

	private static final long serialVersionUID = -5276218624939209139L;

	@NotNull
	private Long customerId;

	@NotBlank
	private String paymentPeriod;

	@NotBlank
	private String paymentMethod;
	
	@NotBlank
	private String currency;

	@NotBlank
	private String billingName;

	@NotBlank
	private String billingAddress;

	@NotBlank
	@Email
	@Size(max = 256)
	private String billingEmail;

	@NotBlank
	private String billingCompanyName;

	@NotBlank
	private String billingMobileNumber;

	@NotNull
	private List<SubscriptionItemRequest> items;

	@JsonCreator
	private SubscriptionRequest(@JsonProperty Long customerId,
			@JsonProperty String paymentPeriod,
			@JsonProperty String paymentMethod,
			@JsonProperty String currency,
			@JsonProperty String billingName,
			@JsonProperty String billingAddress,
			@JsonProperty String billingEmail,
			@JsonProperty String billingCompanyName,
			@JsonProperty String billingMobileNumber,
			@JsonProperty List<SubscriptionItemRequest> items) {

		this.customerId = customerId;
		this.paymentPeriod = paymentPeriod;
		this.paymentMethod = paymentMethod;
		this.currency= currency;
		this.billingName = billingName;
		this.billingAddress = billingAddress;
		this.billingEmail = billingEmail;
		this.billingCompanyName = billingCompanyName;
		this.billingMobileNumber = billingMobileNumber;
		this.items = items;
	}

	public Subscription toEntity() {
		Customer customer = Customer.builder().id(customerId).build();

		List<SubscriptionItem> subscriptionItems = items.stream().map(i -> i.toEntity()).collect(Collectors.toList());

		Subscription subscription = Subscription.builder()
				.customer(customer)
				.paymentPeriod(PaymentPeriod.valueOf(this.paymentPeriod))
				.paymentMethod(PaymentMethod.valueOf(this.paymentMethod))
				.currency(Currency.valueOf(this.currency))
				.billingName(this.billingName)
				.billingAddress(this.billingAddress)
				.billingEmail(this.billingEmail)
				.billingCompanyName(this.billingCompanyName)
				.billingMobileNumber(this.billingMobileNumber)
				.build();

		subscription.addItems(subscriptionItems);
		return subscription;
	}

}
