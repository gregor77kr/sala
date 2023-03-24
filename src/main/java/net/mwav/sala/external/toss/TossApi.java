package net.mwav.sala.external.toss;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class TossApi extends DefaultApi20 {

	private TossApi() {
	}

	private static class InstanceHolder {
		private static final TossApi INSTANCE = new TossApi();
	}

	public static TossApi instance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return null;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return null;
	}

	public String getBillingBaseUrl() {
		return "https://api.tosspayments.com/v1";
	}

	public String getBillingKeyEndPoint() {
		return getBillingBaseUrl() + "/billing/authorizations/card";
	}

	public String getBillingEndPoint() {
		return getBillingBaseUrl() + "/billing";
	}

}
