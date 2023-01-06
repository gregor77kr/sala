package net.mwav.sala.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

	@NotBlank
	private final String name;

	@NotBlank
	private final String password;
}
