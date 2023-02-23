package net.mwav.sala.external.toss;

import java.util.Base64;
import java.util.Optional;

import org.springframework.http.HttpHeaders;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.global.util.WebClientUtil;

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

		Optional<String> response = WebClientUtil.createClient()
				.post()
				.uri(this.tossApi.getBillingKeyEndPoint())
				.headers(header -> {
					header.add(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
				})
				.bodyValue(tossBillingKeyRequest)
				.retrieve()
				.bodyToMono(String.class)
				.flux()
				.toStream()
				.findFirst();
	}

}
