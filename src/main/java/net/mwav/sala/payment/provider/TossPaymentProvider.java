package net.mwav.sala.payment.provider;

import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.payment.provider.dto.BillingKeyRequest;
import net.mwav.sala.payment.provider.dto.BillingKeyResponse;
import net.mwav.sala.payment.provider.dto.TossBillingKeyRequest;

@Slf4j
public class TossPaymentProvider implements PaymentProvider {

	@Override
	public BillingKeyResponse getBillingKey(BillingKeyRequest billingKeyRequest) {
		TossBillingKeyRequest parameter = (TossBillingKeyRequest) billingKeyRequest;

		log.debug(parameter.toString());
		return null;
	}

}
