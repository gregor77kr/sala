package net.mwav.sala.external.toss;

import java.util.Base64;

import org.springframework.http.HttpHeaders;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.external.toss.model.TossBillingKeyResponse;
import net.mwav.sala.global.util.WebClientUtil;
import reactor.core.publisher.Mono;

@Builder
@Slf4j
public class TossProvider {

	private final String appId;

	private final String secret;

	@Builder.Default
	private final TossApi tossApi = TossApi.instance();

	// {@link https://docs.tosspayments.com/guides/using-api/authorization}
	private String getBasicAuthentication() {
		String basicToken = "Basic " + Base64.getEncoder().encodeToString((this.secret + ":").getBytes());
		log.debug(basicToken);
		return basicToken;
	}

	public Mono<TossBillingKeyResponse> getBillingKey(TossBillingKeyRequest tossBillingKeyRequest) {
		log.debug(tossBillingKeyRequest.toString());

		Mono<TossBillingKeyResponse> billingKey = WebClientUtil.createClient()
				.post()
				.uri(this.tossApi.getBillingKeyEndPoint())
				.headers(header -> {
					header.add(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
				})
				.bodyValue(tossBillingKeyRequest)
				.retrieve()
				.bodyToMono(TossBillingKeyResponse.class);

		return billingKey;
	}

}
