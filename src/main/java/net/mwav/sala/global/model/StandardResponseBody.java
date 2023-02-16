package net.mwav.sala.global.model;

import lombok.Getter;
import net.mwav.sala.global.constant.ResponseStatus;

@Getter
public class StandardResponseBody<T> extends AbstractResponseBody<T> {

	private static final long serialVersionUID = -2562620783662103028L;

	private final T data;

	private StandardResponseBody(String status, String message, T data) {
		super(status, message);
		this.data = data;
	}

	public StandardResponseBody(ResponseStatus status) {
		this(status.getStatus(), status.getMessage(), null);
	}

	public StandardResponseBody(ResponseStatus status, T data) {
		this(status.getStatus(), status.getMessage(), data);
	}

	public static <T> StandardResponseBody<T> success() {
		return new StandardResponseBody<T>(ResponseStatus.SUCCESS);
	}

	public static <T> StandardResponseBody<T> success(T data) {
		return new StandardResponseBody<T>(ResponseStatus.SUCCESS, data);
	}

	public static <T> StandardResponseBody<T> fail() {
		return new StandardResponseBody<T>(ResponseStatus.FAIL);
	}

	public static <T> StandardResponseBody<T> fail(T data) {
		return new StandardResponseBody<T>(ResponseStatus.FAIL, data);
	}
}
