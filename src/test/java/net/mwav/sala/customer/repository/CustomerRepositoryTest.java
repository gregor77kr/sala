package net.mwav.sala.customer.repository;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.entity.Customer;

@DataJpaTest
class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void signUpTest() {
		SignUpRequest signUpRequest = SignUpRequest.builder()
				.name("dummyuser")
				.fullname("dummy user")
				.email("admin@mwav.net")
				.password("password")
				.build();

		Customer customer = signUpRequest.toCustomer();
		Customer savedCustomer = customerRepository.save(customer);

		assertEquals(customer, savedCustomer);
	}

}
