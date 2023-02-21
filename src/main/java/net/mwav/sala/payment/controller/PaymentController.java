package net.mwav.sala.payment.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.global.model.StandardResponseBody;
import net.mwav.sala.payment.controller.dto.BootpayBillingRequest;
import net.mwav.sala.payment.controller.dto.TossBillingRequest;
import net.mwav.sala.payment.service.PaymentService;

@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping(value = "/api/payment/bootpay", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> payInBootpay(@Valid @RequestBody BootpayBillingRequest bootpayBillingRequest) throws IOException, InterruptedException, ExecutionException {
		paymentService.payInBootpay(bootpayBillingRequest);
		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success();

		return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
	}

	@PostMapping(value = "/api/payment/toss", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> payInToss(@Valid @RequestBody TossBillingRequest tossBillingRequest) throws IOException, InterruptedException, ExecutionException {
		paymentService.payInToss(tossBillingRequest);
		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success();

		return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
	}

}
