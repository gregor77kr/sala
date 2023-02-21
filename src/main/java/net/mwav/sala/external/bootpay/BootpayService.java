package net.mwav.sala.external.bootpay;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BootpayService {

	@Value("${api.bootpay.app-id}")
	private String appId;

	@Value("${api.bootpay.secret}")
	private String secret;

	@Value("${api.bootpay.pg}")
	private String pg;

	public void getAccessToken() throws IOException, InterruptedException, ExecutionException {
		BootpayProvider bootpayProvider = BootpayProvider.builder()
				.appId(this.appId)
				.secret(this.secret)
				.build();
		
		//bootpayProvider.getAccessToken();
		log.debug(bootpayProvider.getAccessToken().toString());
	}

}
