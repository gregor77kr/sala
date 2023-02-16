package net.mwav.sala.subscription.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscriptionStatus {

	CREATED("CREATED"),
	PENDING("PENDING"),
	FAILED("FAILED"),
	COMPLETED("COMPLETED"),
	CANCELED("CANCELED"),
	REFUNDED("REFUNDED");

	private final String status;

}
