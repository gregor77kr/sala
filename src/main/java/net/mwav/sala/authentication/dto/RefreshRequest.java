package net.mwav.sala.authentication.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class RefreshRequest implements Serializable {

	private static final long serialVersionUID = 4071025165318282640L;

	@NotBlank
	private final String accessToken;

	@JsonCreator
	private RefreshRequest(@JsonProperty String accessToken) {
		this.accessToken = accessToken;
	}

}
