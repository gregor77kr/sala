package net.mwav.sala.common.dto;

import org.springframework.http.HttpStatus.Series;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {

	private final int code;

	private final Series type;

	private final String detail;

}
