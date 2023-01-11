package net.mwav.sala.authentication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {

	private final String accessToken;

	private final String refreshToken;

	@JsonIgnore
	private final String cookieValue;

	private final String tokenType = "Bearer";

}
