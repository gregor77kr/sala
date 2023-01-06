package net.mwav.sala.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscriptionStatus {

	PENDING("PENDING"),
	FAILED("FAILED"),
	COMPLETED("COMPLETED"),
	HOLD("HOLD"),
	CANCELED("CANCELED"),
	REFUNDED("REFUNDED");

	private final String status;

}
