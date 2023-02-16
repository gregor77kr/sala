package net.mwav.sala.payment.provider.dto;

import lombok.Builder;
import lombok.Value;
import net.mwav.sala.payment.entity.Payment;

@Value
@Builder
public class TossBillingKeyRequest implements BillingKeyRequest {

	private static final long serialVersionUID = 4254927558647508918L;

	private String customerIdentityNumber;

	private String customerKey;

	private String cardNumber;

	private String cardExpirationYear;

	private String cardExpirationMonth;

	private String cardPassword;

	public static TossBillingKeyRequest from(Payment payment) {
		return TossBillingKeyRequest.builder()
				.customerIdentityNumber(String.valueOf(payment.getCustomer().getId()))
				.customerKey(payment.getSubscriptionNo())
				.cardNumber(payment.getCardNumber())
				.cardExpirationYear(payment.getCardExpirationYear())
				.cardExpirationMonth(payment.getCardExpirationMonth())
				.cardPassword(payment.getCardPassword())
				.build();
	}

}
