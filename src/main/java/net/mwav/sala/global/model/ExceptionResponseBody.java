package net.mwav.sala.global.model;

import lombok.Getter;
import net.mwav.sala.global.model.constant.ResponseStatus;

@Getter
public class ExceptionResponseBody<T> extends AbstractResponseBody<T> {

	private static final long serialVersionUID = 787595164170691327L;

	private final T error;

	private ExceptionResponseBody(T error) {
		super(ResponseStatus.FAIL.getStatus(), ResponseStatus.FAIL.getMessage());
		this.error = error;
	}

	public static <T> ExceptionResponseBody<T> create(T error) {
		return new ExceptionResponseBody<T>(error);
	}
}
