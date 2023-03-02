package net.mwav.sala.billing.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TossRequest implements Serializable {

	private static final long serialVersionUID = -6862839646726014430L;

	private final Long customerId;

	private final String subscriptionNo;

	private final String orderNo;

	private final String orderName;

	private final String customerEmail;

	private final String customerName;

	private final String customerMobilePhone;

	@Builder.Default
	private final int taxFreeAmount = 0;

	private final int cardInstallmentPlan;

	private final int amount;

	private final String cardNumber;

	private final String cardExpirationYear;

	private final String cardExpirationMonth;

	private final String cardPassword;

	private final String customerIdentityNumber;

	@JsonCreator
	public TossRequest(@JsonProperty Long customerId,
			@JsonProperty String subscriptionNo,
			@JsonProperty String orderNo,
			@JsonProperty String orderName,
			@JsonProperty String customerEmail,
			@JsonProperty String customerName,
			@JsonProperty String customerMobilePhone,
			@JsonProperty int taxFreeAmount,
			@JsonProperty int cardInstallmentPlan,
			@JsonProperty int amount,
			@JsonProperty String cardNumber,
			@JsonProperty String cardExpirationYear,
			@JsonProperty String cardExpirationMonth,
			@JsonProperty String cardPassword,
			@JsonProperty String customerIdentityNumber) {

		this.customerId = customerId;
		this.subscriptionNo = subscriptionNo;
		this.orderNo = orderNo;
		this.orderName = orderName;
		this.customerEmail = customerEmail;
		this.customerName = customerName;
		this.customerMobilePhone = customerMobilePhone;
		this.taxFreeAmount = taxFreeAmount;
		this.cardInstallmentPlan = cardInstallmentPlan;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.cardExpirationYear = cardExpirationYear;
		this.cardExpirationMonth = cardExpirationMonth;
		this.cardPassword = cardPassword;
		this.customerIdentityNumber = customerIdentityNumber;
	}

}
