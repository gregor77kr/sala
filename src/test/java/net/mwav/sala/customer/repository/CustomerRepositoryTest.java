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
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setName("dummyuser");
		signUpRequest.setFullname("dummy user");
		signUpRequest.setEmail("admin@mwav.net");
		signUpRequest.setPassword("password");

		Customer customer = signUpRequest.toEntity();
		Customer savedCustomer = customerRepository.save(customer);

		assertEquals(customer, savedCustomer);
	}

}
