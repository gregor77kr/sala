package net.mwav.sala.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -8388551690394192571L;

	@NotBlank
	private final long customerId;

	@NotBlank
	private final String authenticationCode;

}
