package net.mwav.sala.payment.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class BootpayPaymentRequest implements Serializable {

	private static final long serialVersionUID = 556009166525164392L;

	@NotNull
	private Long customerId;

	@NotBlank
	private String subscriptionNo;

	@NotBlank
	private String cardNumber;

	@NotBlank
	private String cardExpirationYear;

	@NotBlank
	private String cardExpirationMonth;

	@NotBlank
	private String cardPassword;

	@JsonCreator
	public BootpayPaymentRequest(@JsonProperty Long customerId,
			@JsonProperty String subscriptionNo,
			@JsonProperty String cardNumber,
			@JsonProperty String cardExpirationYear,
			@JsonProperty String cardExpirationMonth,
			@JsonProperty String cardPassword) {

		this.customerId = customerId;
		this.subscriptionNo = subscriptionNo;
		this.cardNumber = cardNumber;
		this.cardExpirationYear = cardExpirationYear;
		this.cardExpirationMonth = cardExpirationMonth;
		this.cardPassword = cardPassword;
	}

}
