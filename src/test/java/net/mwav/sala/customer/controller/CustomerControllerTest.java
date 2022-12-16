package net.mwav.sala.customer.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import net.mwav.sala.common.util.JsonUtils;
import net.mwav.sala.customer.dto.SignUpRequest;
import net.mwav.sala.customer.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	private static final String SIGN_UP_URL = "/customer/sign-up";

	@Test
	void signUpTest() throws Exception {

		SignUpRequest signUpRequest = SignUpRequest.builder()
				.name("dummyuser")
				.fullname("dummy user")
				.email("admin@mwav.net")
				.password("password1@")
				.build();

		mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_URL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(JsonUtils.convertToJson(signUpRequest))).andExpect(status().isCreated());
	}

	@Test
	void signUpNullTest() throws Exception {

		SignUpRequest signUpRequest = SignUpRequest.builder().build();

		mockMvc.perform(MockMvcRequestBuilders.post(SIGN_UP_URL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(JsonUtils.convertToJson(signUpRequest))).andExpect(status().isBadRequest());
	}

}
