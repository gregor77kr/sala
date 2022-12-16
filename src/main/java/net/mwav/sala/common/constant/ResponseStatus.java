package net.mwav.sala.common.constant;

import lombok.Getter;

@Getter
public enum ResponseStatus {

	SUCCESS("SUCCESS", "Request fullfilled."),
	FAIL("FAILED", "Request failed. See detail error message.");

	private final String status;

	private final String message;

	ResponseStatus(String status, String message) {
		this.status = status;
		this.message = message;
	}

}
