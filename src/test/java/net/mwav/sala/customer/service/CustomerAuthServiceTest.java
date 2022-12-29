package net.mwav.sala.customer.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.entity.CustomerAuth;
import net.mwav.sala.customer.repository.CustomerAuthRepository;
import net.mwav.sala.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerAuthServiceTest {

	@InjectMocks
	private CustomerAuthService customerAuthService;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CustomerAuthRepository customerAuthRepository;

	@Test
	public void sendTest() throws Exception {
		Customer customer = Customer.builder("dummyuser").id(1).email("admin@mwav.net").build();

		when(customerRepository.findById((long) 1))
				.thenReturn(Optional.ofNullable(customer));

		when(customerAuthRepository.findOneByCustomerId(1))
				.thenReturn(Optional.ofNullable(CustomerAuth.builder(customer).build()));

		customerAuthService.sendAuthentication(1);
	}
}
