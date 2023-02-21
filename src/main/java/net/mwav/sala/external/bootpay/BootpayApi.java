package net.mwav.sala.external.bootpay;

import com.github.scribejava.core.builder.api.DefaultApi20;

class BootpayApi extends DefaultApi20 {

	private BootpayApi() {
	}

	private static class InstanceHolder {
		private static final BootpayApi INSTANCE = new BootpayApi();
	}

	public static BootpayApi instance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return "https://api.bootpay.co.kr/v2/request/token.json";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return null;
	}

}
