package net.mwav.sala.payment.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
import net.mwav.sala.payment.provider.dto.BillingKeyRequest;
import net.mwav.sala.payment.provider.dto.TossBillingKeyRequest;

/**
 * Saving this information in database is illegal.
 * {@link https://www.law.go.kr/LSW/precInfoP.do?precSeq=84568}
 *
 */
@Value
public class TossPaymentRequest implements PaymentRequest {

	private static final long serialVersionUID = 7528051185262350288L;

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
	public TossPaymentRequest(@JsonProperty Long customerId,
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

	@Override
	public BillingKeyRequest toBillingKey() {
		TossBillingKeyRequest tossBillingKeyRequest = TossBillingKeyRequest.builder()
				.customerIdentityNumber(String.valueOf(this.customerId))
				.customerKey(this.subscriptionNo)
				.cardNumber(this.cardNumber)
				.cardExpirationYear(this.cardExpirationYear)
				.cardExpirationMonth(this.cardExpirationMonth)
				.cardPassword(this.cardPassword)
				.build();

		return tossBillingKeyRequest;
	}

}
