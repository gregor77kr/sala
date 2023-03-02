package net.mwav.sala.external.toss.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TossBillingRequest implements Serializable {

	private static final long serialVersionUID = 516700522419215722L;

	private final String customerKey;

	private final String billingKey;

	private final String orderId;

	private final String orderName;

	private final String customerEmail;

	private final String customerName;

	private final String customerMobilePhone;

	private final int amount;

	@Builder.Default
	private final int taxFreeAmount = 0;

	private final int cardInstallmentPlan;

	@JsonCreator
	public TossBillingRequest(
			@JsonProperty String customerKey,
			@JsonProperty String billingKey,
			@JsonProperty String orderId,
			@JsonProperty String orderName,
			@JsonProperty String customerEmail,
			@JsonProperty String customerName,
			@JsonProperty String customerMobilePhone,
			@JsonProperty int amount,
			@JsonProperty int taxFreeAmount,
			@JsonProperty int cardInstallmentPlan) {

		this.customerKey = customerKey;
		this.billingKey = billingKey;
		this.orderId = orderId;
		this.orderName = orderName;
		this.customerEmail = customerEmail;
		this.customerName = customerName;
		this.customerMobilePhone = customerMobilePhone;
		this.amount = amount;
		this.taxFreeAmount = taxFreeAmount;
		this.cardInstallmentPlan = cardInstallmentPlan;
	}

}
