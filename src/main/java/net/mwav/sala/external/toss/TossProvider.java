package net.mwav.sala.external.toss;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
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
		return "Basic " + Base64.getEncoder().encodeToString((this.secret + ":").getBytes());
	}

	public void getBillingKey(TossBillingKeyRequest tossBillingKeyRequest) {
		log.debug(tossBillingKeyRequest.toString());

		Mono<?> webClient = WebClient.create(this.tossApi.getBillingBaseUrl())
				.post()
				.uri(this.tossApi.getBillingKeyEndPoint())
				.headers(h -> {
					h.add(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
				})
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(tossBillingKeyRequest)
				.retrieve()
				.bodyToMono(Object.class);

		webClient.subscribe(o -> {
			log.info(o.toString());
		});
	}

}
