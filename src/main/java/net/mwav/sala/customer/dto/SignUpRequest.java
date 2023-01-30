package net.mwav.sala.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;
import net.mwav.sala.customer.entity.Customer;

@Value
public class SignUpRequest implements Serializable {

	private static final long serialVersionUID = 6084375370182024681L;

	@NotBlank
	@Size(min = 5, max = 25)
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영어 대소문자, 숫자만 사용할 수 있습니다.")
	private final String name;

	@NotNull
	@Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,32}", message = "비밀번호는 8~32자 길이의 영어 소문자, 특수문자, 숫자가 포함된 문자입니다.")
	private final String password;

	@NotBlank
	@Size(min = 1, max = 50)
	private final String fullname;

	@NotBlank
	@Email
	@Size(max = 256)
	private final String email;

	@JsonCreator
	private SignUpRequest(@JsonProperty String name, @JsonProperty String password, @JsonProperty String fullname, @JsonProperty String email) {
		this.name = name;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
	}

	public Customer toEntity() {
		return Customer.builder(this.name)
			.password(this.password)
			.fullname(this.fullname)
			.email(this.email)
			.build();
	}
}
