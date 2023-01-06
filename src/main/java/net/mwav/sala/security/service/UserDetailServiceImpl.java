package net.mwav.sala.security.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.repository.CustomerRepository;
import net.mwav.sala.security.dto.AuthenticationRequest;
import net.mwav.sala.security.dto.CustomerDetails;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final CustomerRepository customerRepository;

	@Override
	public CustomerDetails loadUserByUsername(String customerId) throws UsernameNotFoundException {
		Customer customer = customerRepository.findOneById(Long.valueOf(customerId));
		return CustomerDetails.from(customer);
	}

	public CustomerDetails authenticate(AuthenticationRequest authenticationRequest) throws NoSuchAlgorithmException {
		// name, password compare
		Customer customer = customerRepository.findOneByName(authenticationRequest.getName());

		if (!customer.matchPassword(authenticationRequest.getPassword())) {
			throw new BadCredentialsException("아이디 혹은 비밀번호를 확인해주세요.");
		}

		return CustomerDetails.from(customer);
	}

}
