package net.mwav.sala.security.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Value;
import net.mwav.sala.common.constant.Role;
import net.mwav.sala.customer.entity.Customer;

@Value
@Builder
public class CustomerDetails implements UserDetails {

	private static final long serialVersionUID = 8011018974492519912L;

	private final long id;

	private final String password;

	private final String salt;

	private final String fullname;

	private final String name;

	private final String email;

	private final boolean isEnabled;

	@Builder.Default
	private final List<GrantedAuthority> roles = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return String.valueOf(this.id);
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
		CustomerDetails customerDetails = CustomerDetails.builder()
				.id(customer.getId())
				.password(customer.getPassword())
				.salt(customer.getSalt())
				.name(customer.getName())
				.fullname(customer.getFullname())
				.email(customer.getEmail())
				.isEnabled(customer.isEnabled())
				.build();

		// TODO : 권한 테이블 생성 및 사용자 권한 체계 부여
		customerDetails.roles.add(new Authority(Role.USER.getRole()));

		return customerDetails;
	}
}
