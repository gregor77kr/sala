package net.mwav.sala.payment.provider;

import net.mwav.sala.payment.provider.dto.BillingKeyRequest;
import net.mwav.sala.payment.provider.dto.BillingKeyResponse;
import net.mwav.sala.payment.provider.dto.TossBillingKeyRequest;

public abstract class PaymentProvider {

	protected BillingKeyRequest billingKeyRequest;

	protected PaymentProvider() {

	}

	public static PaymentProvider getProvider(BillingKeyRequest billingKeyRequest) {
		if (billingKeyRequest instanceof TossBillingKeyRequest) {
			return new TossPaymentProvider(billingKeyRequest);
		} else {
			return new TossPaymentProvider(billingKeyRequest);
		}
	}

	public abstract BillingKeyResponse fetchBillingKey();

}
