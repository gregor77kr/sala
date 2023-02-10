package net.mwav.sala.global.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public abstract class AbstractResponseBody<T> implements Serializable {

	private static final long serialVersionUID = -3634021594777117139L;

	private final String status;

	private final String message;

	protected AbstractResponseBody() {
		this(null, null);
	}

	protected AbstractResponseBody(String status, String message) {
		this.status = status;
		this.message = message;
	}

}
