package net.mwav.sala.subscription.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.common.dto.StandardResponseBody;
import net.mwav.sala.subscription.dto.SubscriptionRequest;
import net.mwav.sala.subscription.service.SubscriptionService;

@RestController
@RequestMapping(value = "subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

	private final SubscriptionService subscriptionService;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> subscribe(@Valid @RequestBody SubscriptionRequest subscribeRequest) {
		StandardResponseBody<?> response = StandardResponseBody
				.success(subscriptionService.subscribe(subscribeRequest));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
