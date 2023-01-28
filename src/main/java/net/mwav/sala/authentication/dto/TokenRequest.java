package net.mwav.sala.authentication.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TokenRequest implements Serializable {

	private static final long serialVersionUID = 5214885088617354959L;

	@NotBlank
	@Size(min = 5, max = 25)
	private String name;

	@NotNull
	@Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,32}", message = "비밀번호는 8~32자 길이의 영어 소문자, 특수문자, 숫자가 포함된 문자입니다.")
	private String password;
}
