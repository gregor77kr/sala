package net.mwav.sala.authentication.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.authentication.entity.CustomerToken;
import net.mwav.sala.authentication.jwt.JwtTokenProvider;
import net.mwav.sala.authentication.repository.CustomerTokenRepository;
import net.mwav.sala.common.constant.Role;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.security.dto.Authority;
import net.mwav.sala.security.service.SecurityService;

@Service
@RequiredArgsConstructor
public class AuthenticationTokenService {

	private final JwtTokenProvider jwtTokenProvider;

	private final SecurityService securityService;

	private final CustomerTokenRepository customerTokenRepository;

	@Transactional
	public CustomerToken createToken(String name, String password) throws Exception {
		// authenticate
		Authentication authentication = securityService.authenticate(name, password);

		// create jwt token
		String subject = authentication.getName();
		Collection<?> authorities = authentication.getAuthorities();

		String accessToken = jwtTokenProvider.createAccessToken(subject, authorities);
		String refreshtoken = jwtTokenProvider.createRefreshToken(subject);

		Customer customer = Customer.builder().id(Long.valueOf(subject)).build();
		CustomerToken customerToken = CustomerToken.builder()
				.customer(customer)
				.accessToken(accessToken)
				.refreshToken(refreshtoken)
				.build();

		customerToken = customerTokenRepository.save(customerToken);

		// return token
		return customerToken;
	}

	@Transactional
	public CustomerToken reissue(String refreshToken) {
		String subject = jwtTokenProvider.getSubject(refreshToken);
		CustomerToken customerToken = customerTokenRepository
				.findByCustomerIdAndRefreshToken(Long.valueOf(subject), refreshToken)
				.orElseThrow(EntityNotFoundException::new);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new Authority(Role.USER.getRole()));

		customerToken.setAccessToken(jwtTokenProvider.createAccessToken(subject, authorities));
		customerToken.setRefreshToken(jwtTokenProvider.createRefreshToken(subject));

		return customerToken;
	}
}
