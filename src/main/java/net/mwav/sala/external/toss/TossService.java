package net.mwav.sala.external.toss;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.external.toss.model.TossBillingKeyRequest;
import net.mwav.sala.external.toss.model.TossBillingKeyResponse;
import net.mwav.sala.external.toss.model.TossBillingRequest;
import net.mwav.sala.external.toss.model.TossBillingResponse;

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

	public TossBillingKeyResponse getBillingKey(TossBillingKeyRequest tossBillingKeyRequest) throws Exception {
		return tossProvider.getBillingKey(tossBillingKeyRequest);
	}

	public TossBillingResponse pay(TossBillingRequest tossBillingRequest) throws Exception {
		return tossProvider.pay(tossBillingRequest);
	}

}
