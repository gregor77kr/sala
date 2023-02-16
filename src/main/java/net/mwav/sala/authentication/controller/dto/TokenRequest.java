package net.mwav.sala.authentication.controller.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class TokenRequest implements Serializable {

	private static final long serialVersionUID = 5214885088617354959L;

	@NotBlank
	@Size(min = 5, max = 25)
	private final String name;

	@NotNull
	@Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,32}", message = "비밀번호는 8~32자 길이의 영어 소문자, 특수문자, 숫자가 포함된 문자입니다.")
	private final String password;

	@JsonCreator
	private TokenRequest(@JsonProperty String name, @JsonProperty String password) {
		this.name = name;
		this.password = password;
	}

}
