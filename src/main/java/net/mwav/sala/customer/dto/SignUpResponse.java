package net.mwav.sala.customer.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;
import net.mwav.sala.customer.entity.Customer;

@Value
@Builder
public class SignUpResponse implements Serializable {

	private static final long serialVersionUID = 3039627668671968715L;

	private final long customerId;

	private final boolean isVerified;

	public static SignUpResponse from(Customer customer) {
		return SignUpResponse.builder()
				.customerId(customer.getId())
				.isVerified(customer.isVerified())
				.build();
	}

}
