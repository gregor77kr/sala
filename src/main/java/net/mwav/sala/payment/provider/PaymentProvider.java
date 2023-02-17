package net.mwav.sala.payment.provider;

import net.mwav.sala.payment.provider.model.BillingKeyRequest;
import net.mwav.sala.payment.provider.model.BillingKeyResponse;

public abstract class PaymentProvider {

	public abstract BillingKeyResponse fetchBillingKey(BillingKeyRequest billingKeyRequest);

}
