package net.mwav.sala.payment.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.payment.controller.dto.PaymentRequest;
import net.mwav.sala.payment.provider.PaymentProvider;
import net.mwav.sala.payment.provider.dto.BillingKeyRequest;

@Service
@RequiredArgsConstructor
public class PaymentService {

	public void pay(PaymentRequest paymentRequest) {
		// toss payment와 api 통신
		BillingKeyRequest billingKeyRequest = paymentRequest.toBillingKey();

		PaymentProvider paymentProvider = PaymentProvider.getProvider(billingKeyRequest);
		paymentProvider.fetchBillingKey();

		// 받은 정보를 payment entity에 저장
	}

}
