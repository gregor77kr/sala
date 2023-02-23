package net.mwav.sala.external.toss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;

@Service
@RequiredArgsConstructor
public class TossService {

	@Value("${api.toss.app-id}")
	private String appId;

	@Value("${api.toss.secret}")
	private String secret;

	private TossProvider getProvider() {
		TossProvider tossProvider = TossProvider.builder()
				.appId(this.appId)
				.secret(this.secret)
				.build();
		return tossProvider;
	}

	public void getBillingKey(TossBillingKeyRequest tossBillingKeyRequest) {
		TossProvider tossProvider = getProvider();
		tossProvider.getBillingKey(tossBillingKeyRequest);
	}
}