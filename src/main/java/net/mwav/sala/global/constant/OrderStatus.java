package net.mwav.sala.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
	
	CREATED("CREATED"),
	PENDING("PENDING"),
	FAILED("FAILED"),
	COMPLETED("COMPLETED"),
	CANCELED("CANCELED"),
	REFUNDED("REFUNDED");

	private final String status;
	
}
