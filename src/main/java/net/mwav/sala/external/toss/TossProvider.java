package net.mwav.sala.external.toss;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.OAuthResponseException;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.external.toss.model.TossBillingKeyResponse;
import net.mwav.sala.external.toss.model.TossBillingRequest;
import net.mwav.sala.external.toss.model.TossBillingResponse;
import net.mwav.sala.global.util.JsonUtils;

@Builder
@Slf4j
public class TossProvider {

	private final String appId;

	private final String secret;

	@Builder.Default
	private final TossApi tossApi = TossApi.instance();

	// {@link https://docs.tosspayments.com/guides/using-api/authorization}
	private String getBasicAuthentication() {
		String basicToken = "Basic " + Base64.getEncoder()
				.encodeToString((this.secret + ":").getBytes());
		log.debug(basicToken);
		return basicToken;
	}

	public TossBillingKeyResponse getBillingKey(TossBillingKeyRequest tossBillingKeyRequest) throws Exception {
		OAuth20Service service = new ServiceBuilder(this.appId)
				.apiSecret(this.secret)
				.build(this.tossApi);

		OAuthRequest request = new OAuthRequest(Verb.POST, this.tossApi.getBillingKeyEndPoint());
		request.addHeader(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
		request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		request.setPayload(JsonUtils.convertToJson(tossBillingKeyRequest));
		log.debug("request : {}, payload : {}", request.toString(), request.getStringPayload());

		Response response = service.execute(request);
		if (!response.isSuccessful()) {
			throw new OAuthResponseException(response);
		}

		TossBillingKeyResponse tossBillingKeyResponse = JsonUtils.convertToObject(response.getBody(),
				TossBillingKeyResponse.class);
		log.debug(tossBillingKeyResponse.toString());

		return tossBillingKeyResponse;
	}

	public TossBillingResponse pay(TossBillingRequest tossBillingRequest) throws Exception {
		OAuth20Service service = new ServiceBuilder(this.appId)
				.apiSecret(this.secret)
				.build(this.tossApi);

		OAuthRequest request = new OAuthRequest(Verb.POST,
				this.tossApi.getBillingEndPoint() + "/" + tossBillingRequest.getBillingKey());
		request.addHeader(HttpHeaders.AUTHORIZATION, getBasicAuthentication());
		request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		request.setPayload(JsonUtils.convertToJson(tossBillingRequest));
		log.debug("request : {}, payload : {}", request.toString(), request.getStringPayload());

		Response response = service.execute(request);
		if (!response.isSuccessful()) {
			throw new OAuthResponseException(response);
		}

		TossBillingResponse billing = JsonUtils.convertToObject(response.getBody(),
				TossBillingResponse.class);
		log.debug(billing.toString());

		return billing;
	}

}
