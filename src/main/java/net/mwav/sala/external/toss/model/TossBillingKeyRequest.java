package net.mwav.sala.external.toss.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TossBillingKeyRequest implements Serializable {

	private static final long serialVersionUID = 550687742048913675L;

	private final String customerKey;

	private final String cardNumber;

	private final String cardExpirationYear;

	private final String cardExpirationMonth;

	private final String cardPassword;

	private final String customerIdentityNumber;

	private final String customerName;

	private final String customerEmail;

	@JsonCreator
	public TossBillingKeyRequest(@JsonProperty String customerKey,
			@JsonProperty String cardNumber,
			@JsonProperty String cardExpirationYear,
			@JsonProperty String cardExpirationMonth,
			@JsonProperty String cardPassword,
			@JsonProperty String customerIdentityNumber,
			@JsonProperty String customerName,
			@JsonProperty String customerEmail) {

		this.customerKey = customerKey;
		this.cardNumber = cardNumber;
		this.cardExpirationYear = cardExpirationYear;
		this.cardExpirationMonth = cardExpirationMonth;
		this.cardPassword = cardPassword;
		this.customerIdentityNumber = customerIdentityNumber;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
	}

}
