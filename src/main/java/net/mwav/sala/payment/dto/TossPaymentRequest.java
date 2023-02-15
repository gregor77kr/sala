package net.mwav.sala.payment.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Value;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.global.constant.PaymentProviderType;
import net.mwav.sala.payment.entity.Payment;

/**
 * Saving this information in database is illegal.
 * {@link https://www.law.go.kr/LSW/precInfoP.do?precSeq=84568}
 *
 */
@Value
public class TossPaymentRequest implements Serializable {

	private static final long serialVersionUID = 7528051185262350288L;

	@NotNull
	private long customerId;

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

	public Payment toEntity() {
		Customer customer = Customer.builder().id(customerId).build();

		return Payment.builder()
				.customer(customer)
				.subscriptionNo(subscriptionNo)
				.providerType(PaymentProviderType.TOSS)
				.cardNumber(cardNumber)
				.cardExpirationYear(cardExpirationYear)
				.cardExpirationMonth(cardExpirationMonth)
				.cardPassword(cardPassword)
				.build();
	}

}
