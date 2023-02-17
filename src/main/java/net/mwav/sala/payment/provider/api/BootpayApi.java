package net.mwav.sala.payment.provider.api;

public class BootpayApi extends PaymentApi {

	private static final String BILLING_KEY_END_POINT = "https://api.bootpay.co.kr/v2/request/subscribe";

	private static final String PAYMENT_END_POINT = "https://api.bootpay.co.kr/v2/subscribe/payment";
	
	private BootpayApi() {
    }

    private static class InstanceHolder {
        private static final BootpayApi INSTANCE = new BootpayApi();
    }

    public static BootpayApi instance() {
        return BootpayApi.InstanceHolder.INSTANCE;
    }

	@Override
	public String getBillingKeyEndPoint() {
		return BILLING_KEY_END_POINT;
	}

	@Override
	public String getPaymentEndPoint() {
		return PAYMENT_END_POINT;
	}

}
