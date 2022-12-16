package net.mwav.sala.common.dto;

import org.springframework.http.HttpStatus.Series;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {

	private int code;

	private Series type;

	private String detail;

}
