package net.mwav.sala.authentication.controller.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;
import net.mwav.sala.authentication.entity.CustomerToken;

@Value
@Builder
public class TokenResponse implements Serializable {

	private static final long serialVersionUID = 2098770828232813697L;

	private final String accessToken;

	private final String refreshToken;

	private final String tokenType = "Bearer";

	public static TokenResponse from(CustomerToken customerToken) {
		return TokenResponse.builder()
				.accessToken(customerToken.getAccessToken())
				.refreshToken(customerToken.getRefreshToken())
				.build();
	}
}
