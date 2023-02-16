package net.mwav.sala.subscription.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionStatus {
	
	PENDING("PENDING"),
	FAILED("FAILED"),
	COMPLETED("COMPLETED");

	private final String status;
	
}
