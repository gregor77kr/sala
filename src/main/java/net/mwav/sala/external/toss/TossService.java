package net.mwav.sala.external.toss;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.external.toss.model.TossBillingKeyResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
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

	public void getBillingKey(TossBillingKeyRequest tossBillingKeyRequest) {
		Mono<TossBillingKeyResponse> billingKey = tossProvider.getBillingKey(tossBillingKeyRequest);

		billingKey.subscribe(t -> {
			log.debug(t.toString());
		});

	}
}
