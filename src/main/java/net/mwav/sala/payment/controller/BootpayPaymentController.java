package net.mwav.sala.payment.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.global.model.StandardResponseBody;
import net.mwav.sala.payment.controller.dto.BootpayPaymentRequest;
import net.mwav.sala.payment.service.BootpayPaymentService;

@RestController
@RequiredArgsConstructor
public class BootpayPaymentController {

	private final BootpayPaymentService bootpayPaymentService;

	@PostMapping(value = "/api/payment/bootpay", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> payInBootpay(@Valid @RequestBody BootpayPaymentRequest bootpayPaymentRequest) {

		bootpayPaymentService.pay(bootpayPaymentRequest);
		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success();

		return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
	}
	
}
