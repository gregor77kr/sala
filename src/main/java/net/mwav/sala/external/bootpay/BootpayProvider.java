package net.mwav.sala.external.bootpay;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
class BootpayProvider {

	private final String appId;

	private final String secret;

	private OAuth20Service createService() {
		OAuth20Service service = new ServiceBuilder(this.appId)
				.apiSecret(this.secret)
				.build(BootpayApi.instance());

		return service;
	}

	public OAuth2AccessToken getAccessToken() throws IOException, InterruptedException, ExecutionException {
		log.debug("appId : {}, privateKey : {}", appId, secret);
		OAuth20Service service = createService();
		OAuth2AccessToken accessToken = service.getAccessToken("");
		return accessToken;
	}

	public void getBillingKey() {
	}

}
