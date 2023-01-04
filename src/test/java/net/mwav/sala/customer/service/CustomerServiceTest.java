package net.mwav.sala.customer.service;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;

	@Mock
	private CustomerRepository customerRepository;

	@Test()
	void dupSignUpTest() throws NoSuchAlgorithmException {

		SignUpRequest signUpRequest = SignUpRequest.builder()
				.name("dummyuser")
				.fullname("dummy user")
				.email("admin@mwav.net")
				.password("password")
				.build();

		when(customerRepository.isSignedUp(signUpRequest))
				.thenThrow(DataIntegrityViolationException.class);

		assertThrows(DataIntegrityViolationException.class, () -> {
			customerService.signUp(signUpRequest);
		});
	}

}
