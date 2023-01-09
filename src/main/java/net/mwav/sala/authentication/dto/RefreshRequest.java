package net.mwav.sala.authentication.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshRequest {

	@NotBlank
	private final String accessToken;
}
