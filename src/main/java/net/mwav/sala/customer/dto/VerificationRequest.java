package net.mwav.sala.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class VerificationRequest implements Serializable {

	private static final long serialVersionUID = -8388551690394192571L;

	@NotNull
	private final long customerId;

	@NotBlank
	private final String verificationCode;

	@JsonCreator
	private VerificationRequest(@JsonProperty long customerId, @JsonProperty String verificationCode) {
		this.customerId = customerId;
		this.verificationCode = verificationCode;
	}
	
}
