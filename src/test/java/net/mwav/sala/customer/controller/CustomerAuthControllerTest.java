package net.mwav.sala.customer.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

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
import net.mwav.sala.customer.service.CustomerAuthService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerAuthController.class)
public class CustomerAuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerAuthService customerAuthService;

	@Test
	void sendAuthenticationTest() throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("customerId", 1);

		mockMvc.perform(MockMvcRequestBuilders.post("/customer/authentication")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(JsonUtils.convertToJson(parameter))).andExpect(status().isCreated());
	}

}
