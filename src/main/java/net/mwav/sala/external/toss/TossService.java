package net.mwav.sala.external.toss;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.external.toss.model.TossBillingKeyResponse;
import net.mwav.sala.external.toss.model.TossBillingResponse;
import net.mwav.sala.payment.controller.dto.TossBillingRequest;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TossService {

	@Value("${api.toss.app-id}")
	private String appId;

	@Value("${api.toss.secret}")
	private String secret;

	private TossProvider tossProvider;

	@PostConstruct
	private void initialize() {
		this.tossProvider = TossProvider.builder()
				.appId(this.appId)
				.secret(this.secret)
				.build();
	}

	public Mono<TossBillingKeyResponse> getBillingKey(TossBillingKeyRequest tossBillingKeyRequest) {
		Mono<TossBillingKeyResponse> billingKey = tossProvider.getBillingKey(tossBillingKeyRequest);
		return billingKey;
	}

	public Mono<TossBillingResponse> pay(TossBillingRequest tossBillingRequest) {
		Mono<TossBillingResponse> billing = tossProvider.pay(tossBillingRequest);
		return billing;
	}

}
