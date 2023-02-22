package net.mwav.sala.external.toss;

import java.util.Base64;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.global.util.JsonUtils;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Builder
@Slf4j
public class TossProvider {

	private final String appId;

	private final String secret;

	@Builder.Default
	private final TossApi tossApi = TossApi.instance();

	// {@link https://docs.tosspayments.com/guides/using-api/authorization}
	private String getBasicAuthentication() {
		log.debug("Basic " + Base64.getEncoder().encodeToString((this.secret + ":").getBytes()));
		return "Basic " + Base64.getEncoder().encodeToString((this.secret + ":").getBytes());
	}

	public void getBillingKey(TossBillingKeyRequest tossBillingKeyRequest) throws Exception {
		log.debug(tossBillingKeyRequest.toString());

		HttpClient httpClient = HttpClient.create()
				.secure(t -> {
					try {
						t.sslContext(SslContextBuilder
								.forClient()
								.trustManager(InsecureTrustManagerFactory.INSTANCE)
								.build());
					} catch (SSLException e) {
						e.printStackTrace();
					}
				});

		log.debug(JsonUtils.convertToJson(tossBillingKeyRequest));

		Mono<?> webClient = WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.baseUrl(this.tossApi.getBillingBaseUrl())
				.build()
				.post()
				.uri(this.tossApi.getBillingKeyEndPoint())
				.headers(h -> {
					h.add(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
				})
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(JsonUtils.convertToJson(tossBillingKeyRequest))
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
				});

		log.debug(webClient.toString());

		webClient.subscribe(o -> {
			log.debug(o.toString());
		});
	}

}
