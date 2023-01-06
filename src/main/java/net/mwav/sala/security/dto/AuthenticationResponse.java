package net.mwav.sala.security.dto;

import lombok.Builder;
import lombok.Data;
import net.mwav.sala.customer.entity.Customer;

@Data
@Builder
public class AuthenticationResponse {

	private final long customerId;

	public static AuthenticationResponse from(Customer customer) {
		return AuthenticationResponse.builder()
				.customerId(customer.getId())
				.build();
	}
}
