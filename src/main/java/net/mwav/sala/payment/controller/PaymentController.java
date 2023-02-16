package net.mwav.sala.payment.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.global.model.StandardResponseBody;
import net.mwav.sala.payment.controller.dto.TossPaymentRequest;
import net.mwav.sala.payment.entity.Payment;
import net.mwav.sala.payment.service.PaymentService;
import net.mwav.sala.security.service.SecurityResolver;

@RestController
@RequestMapping(value = "/api/payment")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping(value = "/toss", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> payInToss(@Valid @RequestBody TossPaymentRequest tossPaymentRequest) {
		SecurityResolver.authorize(tossPaymentRequest.getCustomerId());

		Payment payment = tossPaymentRequest.toEntity();

		paymentService.pay(payment);
		StandardResponseBody<?> standardResponseBody = StandardResponseBody.success();

		return ResponseEntity.status(HttpStatus.CREATED).body(standardResponseBody);
	}

}
