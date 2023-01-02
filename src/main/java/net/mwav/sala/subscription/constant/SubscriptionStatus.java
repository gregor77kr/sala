package net.mwav.sala.subscription.constant;

public enum SubscriptionStatus {

	PENDING("PENDING"),
	FAILED("FAILED"),
	COMPLETED("COMPLETED"),
	HOLD("HOLD"),
	CANCELED("CANCELED"),
	REFUNDED("REFUNDED");

	private final String status;

	private SubscriptionStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}
