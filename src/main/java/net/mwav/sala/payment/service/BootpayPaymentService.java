package net.mwav.sala.payment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.payment.controller.dto.BootpayPaymentRequest;
import net.mwav.sala.payment.provider.BootpayPaymentProvider;
import net.mwav.sala.payment.provider.api.BootpayApi;
import net.mwav.sala.payment.provider.model.BootpayBillingKeyRequest;

@Service
@RequiredArgsConstructor
public class BootpayPaymentService {

	@Value("${api.bootpay.app-id}")
	private String appId;

	@Value("${api.bootpay.pg}")
	private String pg;

	@Value("${api.bootpay.private-key}")
	private String privateKey;

	private final PaymentService paymentService;

	public void pay(BootpayPaymentRequest bootpayPaymentRequest) {

		// request -> billingkey request
		BootpayBillingKeyRequest bootpayBillingKeyRequest = null;

		// create provider
		BootpayPaymentProvider bootpayPaymentProvider = BootpayPaymentProvider.builder()
				.appId(appId)
				.pg(pg)
				.privateKey(privateKey)
				.paymentApi(BootpayApi.instance())
				.build();
		
		bootpayPaymentProvider.fetchBillingKey(bootpayBillingKeyRequest);

		paymentService.createPayment(null);
	}

}
