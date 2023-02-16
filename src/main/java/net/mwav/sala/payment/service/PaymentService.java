package net.mwav.sala.payment.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.payment.entity.Payment;
import net.mwav.sala.payment.provider.PaymentProvider;
import net.mwav.sala.payment.provider.dto.BillingKeyRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

	public Payment pay(Payment payment) {
		log.debug(payment.toString());

		PaymentProvider paymentProvider = PaymentProvider.getPaymentProvider(payment.getProviderType());

		// payment to billing key request
		BillingKeyRequest billingKeyRequest = BillingKeyRequest.from(payment);

		// call payment delegator
		paymentProvider.getBillingKey(billingKeyRequest);

		return null;
	}

}
