package net.mwav.sala.authentication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenRequest {

	@NotBlank
	@Size(min = 5, max = 25)
	private final String name;

	@NotNull
	@Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,32}", message = "비밀번호는 8~32자 길이의 영어 소문자, 특수문자, 숫자가 포함된 문자입니다.")
	private final String password;
}
