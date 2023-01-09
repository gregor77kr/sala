package net.mwav.sala.authentication.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.authentication.dto.CustomerDetails;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.repository.CustomerRepository;

@RequiredArgsConstructor
@Service
public class CustomerDetailService implements UserDetailsService {

	private final CustomerRepository customerRepository;

	@Override
	public CustomerDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByName(name).orElseThrow(() -> {
			throw new UsernameNotFoundException("아이디 혹은 비밀번호를 확인해주세요.");
		});
		return CustomerDetails.from(customer);
	}

}
