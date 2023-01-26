package net.mwav.sala.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VerificationRequest implements Serializable {

	private static final long serialVersionUID = -8388551690394192571L;

	@NotNull
	private final long customerId;

	@NotBlank
	private final String verificationCode;

}
