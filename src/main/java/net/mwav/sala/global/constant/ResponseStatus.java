package net.mwav.sala.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

	SUCCESS("SUCCESS", "Request fullfilled."),
	FAIL("FAILED", "Request failed. See detail error message.");

	private final String status;

	private final String message;

}
