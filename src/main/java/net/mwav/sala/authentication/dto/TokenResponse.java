package net.mwav.sala.authentication.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenResponse implements Serializable {

	private static final long serialVersionUID = 2098770828232813697L;

	private final String accessToken;

	private final String refreshToken;

	@JsonIgnore
	private final String cookieString;

	private final String tokenType = "Bearer";

}
