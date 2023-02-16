package net.mwav.sala.global.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.global.model.ExceptionDetail;
import net.mwav.sala.global.model.ExceptionResponseBody;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

	// all
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> exception(Exception exception) {
		ExceptionDetail exceptionDetail = ExceptionDetail.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.type(HttpStatus.INTERNAL_SERVER_ERROR.series())
				.detail(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.build();

		log.error("exception", exception);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponseBody.create(exceptionDetail));
	}

	// parameter validation exception
	@ExceptionHandler({ BindException.class })
	public ResponseEntity<Object> bindException(BindException exception) {
		String detail = exception.getFieldErrors().stream().limit(1).map(o -> {
			return "field : " + o.getField() + ", rejected value : " + o.getRejectedValue() + ", reason : "
					+ o.getDefaultMessage();
		}).collect(Collectors.joining(""));

		ExceptionDetail exceptionDetail = ExceptionDetail.builder()
				.code(HttpStatus.BAD_REQUEST.value())
				.type(HttpStatus.BAD_REQUEST.series())
				.detail(detail)
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponseBody.create(exceptionDetail));
	}

	// invalid content type
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<Object> mediaTypeException(Exception exception) {

		ExceptionDetail exceptionDetail = ExceptionDetail.builder()
				.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
				.type(HttpStatus.UNSUPPORTED_MEDIA_TYPE.series())
				.detail(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
				.build();

		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(ExceptionResponseBody.create(exceptionDetail));
	}

}
