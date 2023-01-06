package net.mwav.sala.authentication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

	private final String accessToken;

	public static AuthenticationResponse from(String token) {
		return AuthenticationResponse.builder()
			.accessToken(token)
			.build();
	}
}
