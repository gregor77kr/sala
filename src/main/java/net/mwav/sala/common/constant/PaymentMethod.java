package net.mwav.sala.common.constant;

public enum PaymentMethod {

	CREDIT_CARD("CARD");

	private final String method;

	private PaymentMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return this.method;
	}
}
