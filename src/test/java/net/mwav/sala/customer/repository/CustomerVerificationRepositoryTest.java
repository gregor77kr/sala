package net.mwav.sala.customer.repository;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.entity.CustomerVerification;

@DataJpaTest
public class CustomerVerificationRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerVerificationRepository customerVerificationRepository;

	@Test
	void sendTest() {
		Customer customer = Customer.builder()
				.name("dummyuser")
				.fullname("dummy user")
				.email("admin@mwav.net")
				.password("password")
				.build();

		customer = customerRepository.save(customer);

		CustomerVerification customerVerification1 = CustomerVerification.generate(customer);
		customerVerification1 = customerVerificationRepository.save(customerVerification1);

		CustomerVerification customerVerification2 = CustomerVerification.generate(customer);
		customerVerification2 = customerVerificationRepository.save(customerVerification2);

		assertEquals(customer, customerVerification1.getCustomer());
	}

}
