package net.mwav.sala.payment.provider;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.payment.provider.api.PaymentApi;
import net.mwav.sala.payment.provider.model.BillingKeyRequest;
import net.mwav.sala.payment.provider.model.BillingKeyResponse;

@Builder
@Slf4j
public class BootpayPaymentProvider extends PaymentProvider {

	private final String appId;

	private String pg;

	private String privateKey;

	private PaymentApi paymentApi;

	@Override
	public BillingKeyResponse fetchBillingKey(BillingKeyRequest billingKeyRequest) {
		log.debug("appId : {}, pg : {}, privateKey : {}", appId, pg, privateKey);
		log.debug(paymentApi.getBillingKeyEndPoint());
		return null;
	}

}
