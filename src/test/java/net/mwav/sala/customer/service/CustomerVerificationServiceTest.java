package net.mwav.sala.customer.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.entity.CustomerVerification;
import net.mwav.sala.customer.repository.CustomerVerificationRepository;
import net.mwav.sala.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerVerificationServiceTest {

	@InjectMocks
	private CustomerVerificationService customerVerificationService;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private CustomerVerificationRepository customerVerificationRepository;

	@Test
	public void sendTest() throws Exception {
		Customer customer = Customer.builder("dummyuser").id(1).email("admin@mwav.net").build();

		when(customerRepository.findOneById((long) 1))
				.thenReturn(customer);

		when(customerVerificationRepository.findByCustomerId(1))
				.thenReturn(Optional.ofNullable(CustomerVerification.builder(customer).build()));

		customerVerificationService.sendVerification(1);
	}
}
