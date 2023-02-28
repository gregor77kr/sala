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

	private final int amount;

	private final String customerKey;

	private final String orderId;

	private final String orderName;

	private final String customerEmail;

	private final String customerName;

	private final String customerMobilePhone;

	@Builder.Default
	private final int taxFreeAmount = 0;

	private final int cardInstallmentPlan;

	@JsonCreator
	public TossBillingRequest(@JsonProperty int amount,
			@JsonProperty String customerKey,
			@JsonProperty String orderId,
			@JsonProperty String orderName,
			@JsonProperty String customerEmail,
			@JsonProperty String customerName,
			@JsonProperty String customerMobilePhone,
			@JsonProperty int taxFreeAmount,
			@JsonProperty int cardInstallmentPlan) {

		this.amount = amount;
		this.customerKey = customerKey;
		this.orderId = orderId;
		this.orderName = orderName;
		this.customerEmail = customerEmail;
		this.customerName = customerName;
		this.customerMobilePhone = customerMobilePhone;
		this.taxFreeAmount = taxFreeAmount;
		this.cardInstallmentPlan = cardInstallmentPlan;
	}

}
