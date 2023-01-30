package net.mwav.sala.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class VerificationRequest implements Serializable {

	private static final long serialVersionUID = -8388551690394192571L;

	@NotBlank
	private final String verificationCode;

	@JsonCreator
	private VerificationRequest(@JsonProperty String verificationCode) {
		this.verificationCode = verificationCode;
	}

}
