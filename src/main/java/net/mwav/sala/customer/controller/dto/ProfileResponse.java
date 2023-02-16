package net.mwav.sala.customer.controller.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Value;
import net.mwav.sala.customer.entity.Customer;

@Value
@Builder
public class ProfileResponse implements Serializable {

	private static final long serialVersionUID = 4023415254323492600L;

	private final Long customerId;

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
