package net.mwav.sala.billing.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.billing.controller.dto.TossRequest;
import net.mwav.sala.billing.entity.Billing;
import net.mwav.sala.billing.entity.constant.BillingProviderType;
import net.mwav.sala.billing.repository.BillingRepository;
import net.mwav.sala.external.toss.TossService;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.external.toss.model.TossBillingKeyResponse;
import net.mwav.sala.external.toss.model.TossBillingRequest;

@Service
@RequiredArgsConstructor
public class BillingService {

	private final TossService tossService;

	private final BillingRepository billingRepository;

	public void billInToss(TossRequest tossRequest) throws Exception {
		TossBillingKeyRequest tossBillingKeyRequest = TossBillingKeyRequest.builder()
				.customerKey(tossRequest.getSubscriptionNo())
				.cardNumber(tossRequest.getCardNumber())
				.cardExpirationYear(tossRequest.getCardExpirationYear())
				.cardExpirationMonth(tossRequest.getCardExpirationMonth())
				.customerIdentityNumber(tossRequest.getCustomerIdentityNumber())
				.cardPassword(tossRequest.getCardPassword())
				.build();

		TossBillingKeyResponse billingKey = tossService.getBillingKey(tossBillingKeyRequest);

		TossBillingRequest tossBillingRequest = TossBillingRequest.builder()
				.customerKey(tossRequest.getSubscriptionNo())
				.billingKey(billingKey.getBillingKey())
				.orderId(tossRequest.getOrderNo())
				.orderName(tossRequest.getOrderName())
				.amount(tossRequest.getAmount())
				.build();

		tossService.pay(tossBillingRequest);

		Billing billing = Billing.builder()
				.providerType(BillingProviderType.TOSS)
				.subscriptionNo(tossRequest.getSubscriptionNo())
				.billingKey(billingKey.getBillingKey())
				.build();

		createBilling(billing);
	}

	@Transactional
	public void createBilling(Billing billing) {
		billingRepository.save(billing);
	}

}
