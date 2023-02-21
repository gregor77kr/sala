package net.mwav.sala.payment.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.external.bootpay.BootpayService;
import net.mwav.sala.external.toss.TossService;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.payment.controller.dto.BootpayBillingRequest;
import net.mwav.sala.payment.controller.dto.TossBillingRequest;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final BootpayService bootpayService;

	private final TossService tossService;

	public void payInBootpay(BootpayBillingRequest bootpayBillingRequest) throws IOException, InterruptedException, ExecutionException {
		bootpayService.getAccessToken();
	}

	public void payInToss(TossBillingRequest tossBillingRequest) throws IOException, InterruptedException, ExecutionException {
		TossBillingKeyRequest tossBillingKeyRequest = TossBillingKeyRequest.builder()
				.customerKey(tossBillingRequest.getSubscriptionNo())
				.cardNumber(tossBillingRequest.getCardNumber())
				.cardExpirationYear(tossBillingRequest.getCardExpirationYear())
				.cardExpirationMonth(tossBillingRequest.getCardExpirationMonth())
				.customerIdentityNumber(tossBillingRequest.getCustomerIdentityNumber())
				.cardPassword(tossBillingRequest.getCardPassword())
				.build();

		tossService.getBillingKey(tossBillingKeyRequest);
	}

}
