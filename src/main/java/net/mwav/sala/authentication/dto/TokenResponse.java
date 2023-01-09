package net.mwav.sala.authentication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {

	private final String accessToken;

	private final String refreshToken;

	public static TokenResponse from(String token) {
		return TokenResponse.builder()
				.accessToken(token)
				.build();
	}
}
