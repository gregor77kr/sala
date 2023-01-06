package net.mwav.sala.security.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import net.mwav.sala.customer.entity.Customer;

@Data
@Builder
public class CustomerDetails implements UserDetails {

	private static final long serialVersionUID = 8011018974492519912L;

	private final long customerId;

	private final String password;

	private final String fullname;

	private final String name;

	private final String email;

	private final boolean isEnabled;

	@Builder.Default
	private List<GrantedAuthority> roles = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		roles.add(authority);

		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isEnabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isEnabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isEnabled;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	public static CustomerDetails from(Customer customer) {
		return CustomerDetails.builder()
				.customerId(customer.getId())
				.password(customer.getPassword())
				.name(customer.getName())
				.fullname(customer.getFullname())
				.email(customer.getEmail())
				.isEnabled(customer.isEnabled())
				.build();
	}
}
