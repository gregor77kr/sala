package net.mwav.sala.customer.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import net.mwav.sala.customer.entity.Customer;

@Data
@Builder
public class SignUpResponse implements Serializable {

	private static final long serialVersionUID = 3039627668671968715L;

	private final long customerId;

	private final boolean isAuthenticated;

	public static SignUpResponse map(Customer customer) {
		return SignUpResponse.builder()
				.customerId(customer.getId())
				.isAuthenticated(customer.isAuthenticated())
				.build();
	}

}
