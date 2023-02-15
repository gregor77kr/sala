package net.mwav.sala.payment.provider;

import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.payment.dto.BillingKeyRequest;
import net.mwav.sala.payment.dto.BillingKeyResponse;
import net.mwav.sala.payment.dto.TossBillingKeyRequest;

@Slf4j
public class TossPaymentProvider implements PaymentProvider {

	@Override
	public BillingKeyResponse getBillingKey(BillingKeyRequest billingKeyRequest) {
		TossBillingKeyRequest parameter = (TossBillingKeyRequest) billingKeyRequest;

		log.debug(parameter.toString());
		return null;
	}

}
