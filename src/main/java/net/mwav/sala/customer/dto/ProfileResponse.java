package net.mwav.sala.customer.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import net.mwav.sala.customer.entity.Customer;

@Data
@Builder
public class ProfileResponse implements Serializable {

	private static final long serialVersionUID = 4023415254323492600L;

	private final long customerId;

	private final String name;

	private final String fullname;

	private final String email;

	public static ProfileResponse from(Customer customer) {
		return ProfileResponse.builder()
				.customerId(customer.getId())
				.name(customer.getName())
				.fullname(customer.getFullname())
				.email(customer.getEmail())
				.build();
	}

}
