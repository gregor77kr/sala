package net.mwav.sala.billing.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.billing.controller.dto.TossRequest;
import net.mwav.sala.external.toss.TossService;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.external.toss.model.TossBillingKeyResponse;
import net.mwav.sala.external.toss.model.TossBillingRequest;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BillingService {

	private final TossService tossService;

	public void billInToss(TossRequest tossRequest) {
		TossBillingKeyRequest tossBillingKeyRequest = TossBillingKeyRequest.builder()
				.customerKey(tossRequest.getSubscriptionNo())
				.cardNumber(tossRequest.getCardNumber())
				.cardExpirationYear(tossRequest.getCardExpirationYear())
				.cardExpirationMonth(tossRequest.getCardExpirationMonth())
				.customerIdentityNumber(tossRequest.getCustomerIdentityNumber())
				.cardPassword(tossRequest.getCardPassword())
				.build();

		Mono<TossBillingKeyResponse> tossBillingKeyResponse = tossService.getBillingKey(tossBillingKeyRequest);

		tossBillingKeyResponse.subscribe(billingKey -> {

			TossBillingRequest tossBillingRequest = TossBillingRequest.builder()
					.customerKey(tossRequest.getSubscriptionNo())
					.billingKey(billingKey.getBillingKey())
					.orderId(tossRequest.getOrderNo())
					.orderName(tossRequest.getOrderName())
					.customerEmail(tossRequest.getCustomerEmail())
					.customerName(tossRequest.getCustomerName())
					.customerMobilePhone(tossRequest.getCustomerMobilePhone())
					.amount(tossRequest.getAmount())
					.build();

			tossService.pay(tossBillingRequest).subscribe();
		});
	}

}
