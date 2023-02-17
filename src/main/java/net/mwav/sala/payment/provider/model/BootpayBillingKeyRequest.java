package net.mwav.sala.payment.provider.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BootpayBillingKeyRequest implements BillingKeyRequest {

	private static final long serialVersionUID = 4254927558647508918L;

	private String customerIdentityNumber;

	private String customerKey;

	private String cardNumber;

	private String cardExpirationYear;

	private String cardExpirationMonth;

	private String cardPassword;

}
