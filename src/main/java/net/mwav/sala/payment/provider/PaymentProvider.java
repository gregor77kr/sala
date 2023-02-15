package net.mwav.sala.payment.provider;

import net.mwav.sala.global.constant.PaymentProviderType;
import net.mwav.sala.payment.dto.BillingKeyRequest;
import net.mwav.sala.payment.dto.BillingKeyResponse;

public interface PaymentProvider {

	public static PaymentProvider getPaymentProvider(PaymentProviderType paymentProviderType) {
		PaymentProvider paymentProvider;

		if (paymentProviderType == PaymentProviderType.TOSS) {
			paymentProvider = new TossPaymentProvider();
		} else {
			paymentProvider = new TossPaymentProvider();
		}

		return paymentProvider;
	}

	public BillingKeyResponse getBillingKey(BillingKeyRequest billingKeyRequest);

}
