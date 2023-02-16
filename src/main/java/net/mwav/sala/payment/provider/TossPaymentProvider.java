package net.mwav.sala.payment.provider;

import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.payment.provider.dto.BillingKeyRequest;
import net.mwav.sala.payment.provider.dto.BillingKeyResponse;
import net.mwav.sala.payment.provider.dto.TossBillingKeyRequest;

@Slf4j
public class TossPaymentProvider extends PaymentProvider {

	protected TossPaymentProvider(BillingKeyRequest billingKeyRequest) {
		this.billingKeyRequest = (TossBillingKeyRequest) billingKeyRequest;
	}

	@Override
	public BillingKeyResponse fetchBillingKey() {
		log.debug(this.billingKeyRequest.toString());
		return null;
	}

}
