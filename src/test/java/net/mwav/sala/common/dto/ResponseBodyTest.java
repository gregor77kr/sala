package net.mwav.sala.common.dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import net.mwav.sala.common.constant.ResponseStatus;

class ResponseBodyTest {

	@Test
	void standardResponseBodytest() {
		StandardResponseBody<Object> responseBody = StandardResponseBody.success();
		assertEquals(responseBody.getStatus(), ResponseStatus.SUCCESS.getStatus());
	}

	@Test
	void exceptionResponseBodytest() {
		ExceptionResponse error = ExceptionResponse.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.type(HttpStatus.INTERNAL_SERVER_ERROR.series())
				.detail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.build();

		ExceptionResponseBody<Object> exceptionResponseBody = ExceptionResponseBody.create(error);
		assertEquals(exceptionResponseBody.getStatus(), ResponseStatus.FAIL.getStatus());
	}

}
