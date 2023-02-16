package net.mwav.sala.payment.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.payment.entity.Payment;
import net.mwav.sala.payment.provider.constant.PaymentProviderType;

/**
 * Saving this information in database is illegal.
 * {@link https://www.law.go.kr/LSW/precInfoP.do?precSeq=84568}
 *
 */
@Value
public class TossPaymentRequest implements Serializable {

	private static final long serialVersionUID = 7528051185262350288L;

	@NotBlank
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

	public Payment toEntity() {
		Customer customer = Customer.builder().id(this.customerId).build();

		return Payment.builder()
				.customer(customer)
				.subscriptionNo(this.subscriptionNo)
				.providerType(PaymentProviderType.TOSS)
				.cardNumber(this.cardNumber)
				.cardExpirationYear(this.cardExpirationYear)
				.cardExpirationMonth(this.cardExpirationMonth)
				.cardPassword(this.cardPassword)
				.build();
	}

}
