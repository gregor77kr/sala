package net.mwav.sala.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VerificationRequest implements Serializable {

	private static final long serialVersionUID = -8388551690394192571L;

	@NotNull
	private long customerId;

	@NotBlank
	private String verificationCode;

}
