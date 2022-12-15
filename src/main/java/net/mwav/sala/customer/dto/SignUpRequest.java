package net.mwav.sala.customer.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.mwav.sala.customer.entity.Customer;

@Getter
@Builder
@ToString
public class SignUpRequest implements Serializable {

	private static final long serialVersionUID = 6084375370182024681L;

	@NotBlank
	@Size(min = 5, max = 25)
	private String name;

	@NotBlank
	@Size(min = 8, max = 32)
	private String password;

	@NotBlank
	@Size(min = 1, max = 50)
	private String fullname;

	@NotBlank
	@Email
	@Size(max = 256)
	private String email;

	public Customer toCustomer() {
		return Customer.builder(this.name)
				.password(this.password)
				.fullname(this.fullname)
				.email(this.email)
				.build();
	}
}
