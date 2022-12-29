package net.mwav.sala.customer.repository;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.customer.entity.CustomerAuth;

@DataJpaTest
public class CustomerAuthRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerAuthRepository customerAuthRepository;

	@Test
	void sendTest() {
		Customer customer = Customer.builder("dummyuser")
				.fullname("dummy user")
				.email("admin@mwav.net")
				.password("password")
				.build();

		customer = customerRepository.save(customer);

		CustomerAuth customerAuth1 = CustomerAuth.create(customer);
		customerAuth1 = customerAuthRepository.save(customerAuth1);

		CustomerAuth customerAuth2 = CustomerAuth.create(customer);
		customerAuth2 = customerAuthRepository.save(customerAuth2);

		assertEquals(customer, customerAuth1.getCustomer());
	}

}
