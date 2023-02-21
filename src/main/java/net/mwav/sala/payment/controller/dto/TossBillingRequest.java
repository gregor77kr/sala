package net.mwav.sala.payment.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class TossBillingRequest implements Serializable {

	private static final long serialVersionUID = -6862839646726014430L;

	@NotNull
	private final Long customerId;

	@NotBlank
	private final String subscriptionNo;

	@NotBlank
	private final String cardNumber;

	@NotBlank
	private final String cardExpirationYear;

	@NotBlank
	private final String cardExpirationMonth;

	@NotBlank
	private final String cardPassword;

	@NotBlank
	private final String customerIdentityNumber;

	@JsonCreator
	public TossBillingRequest(@JsonProperty Long customerId,
			@JsonProperty String subscriptionNo,
			@JsonProperty String cardNumber,
			@JsonProperty String cardExpirationYear,
			@JsonProperty String cardExpirationMonth,
			@JsonProperty String cardPassword,
			@JsonProperty String customerIdentityNumber) {

		this.customerId = customerId;
		this.subscriptionNo = subscriptionNo;
		this.cardNumber = cardNumber;
		this.cardExpirationYear = cardExpirationYear;
		this.cardExpirationMonth = cardExpirationMonth;
		this.cardPassword = cardPassword;
		this.customerIdentityNumber = customerIdentityNumber;
	}
	
}
