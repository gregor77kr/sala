package net.mwav.sala.global.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus.Series;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ExceptionDetail implements Serializable {

	private static final long serialVersionUID = 5623970806823149997L;

	private final int code;

	private final Series type;

	private final String detail;

}
