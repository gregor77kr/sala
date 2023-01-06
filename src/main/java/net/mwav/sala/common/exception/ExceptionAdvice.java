package net.mwav.sala.common.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.common.dto.ExceptionResponse;
import net.mwav.sala.common.dto.ExceptionResponseBody;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> exception(Exception exception) {
		ExceptionResponse error = ExceptionResponse.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.type(HttpStatus.INTERNAL_SERVER_ERROR.series())
				.detail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.build();
		
		log.error("exception", exception);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponseBody.create(error));
	}

	@ExceptionHandler({ BindException.class })
	public ResponseEntity<Object> bindException(BindException exception) {
		String detail = exception.getFieldErrors().stream().limit(1).map(o -> {
			return "field : " + o.getField() + ", rejected value : " + o.getRejectedValue() + ", reason : "
					+ o.getDefaultMessage();
		}).collect(Collectors.joining(""));

		ExceptionResponse error = ExceptionResponse.builder()
				.code(HttpStatus.BAD_REQUEST.value())
				.type(HttpStatus.BAD_REQUEST.series())
				.detail(detail)
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponseBody.create(error));
	}

	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<Object> mediaTypeException(Exception exception) {

		ExceptionResponse error = ExceptionResponse.builder()
				.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
				.type(HttpStatus.UNSUPPORTED_MEDIA_TYPE.series())
				.detail(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
				.build();

		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(ExceptionResponseBody.create(error));
	}
}
