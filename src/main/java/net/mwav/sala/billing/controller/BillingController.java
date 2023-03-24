package net.mwav.sala.billing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.billing.controller.dto.TossRequest;
import net.mwav.sala.billing.service.BillingService;
import net.mwav.sala.customer.controller.dto.SignUpResponse;
import net.mwav.sala.global.model.StandardResponseBody;

@RestController
@RequiredArgsConstructor
public class BillingController {

	private final BillingService billingService;
	
	@PostMapping(value = "/api/billing/toss", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> billInToss(@RequestBody TossRequest tossRequest) throws Exception {
		billingService.billInToss(tossRequest);
		StandardResponseBody<SignUpResponse> standardResponseBody = StandardResponseBody
				.success();

		return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
	}
	
}
