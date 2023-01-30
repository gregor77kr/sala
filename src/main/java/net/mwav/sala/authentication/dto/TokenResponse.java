package net.mwav.sala.authentication.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenResponse implements Serializable {

	private static final long serialVersionUID = 2098770828232813697L;

	private final String accessToken;

	private final String refreshToken;

	private final String tokenType = "Bearer";

}
