package net.mwav.sala.subscription.controller.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Value;
import net.mwav.sala.subscription.entity.Subscription;
import net.mwav.sala.subscription.entity.SubscriptionItem;

@Value
@Builder
public class SubscriptionResponse implements Serializable {

	private static final long serialVersionUID = 1032769317320994617L;

	private long id;

	private String no;

	private String status;

	private LocalDateTime creationDateTime;

	private LocalDateTime lastRenewalDateTime;

	private LocalDate nextRenewalDate;

	private String paymentPeriod;

	private String paymentMethod;

	private double subtotalPrice;

	private String billingName;

	private String billingAddress;

	private String billingEmail;

	private String billingCompanyName;

	private String billingMobileNumber;

	private List<SubscriptionItem> items;

	public static SubscriptionResponse from(Subscription subscription) {
		return SubscriptionResponse.builder()
				.id(subscription.getId())
				.no(subscription.getNo())
				.status(subscription.getSubscriptionStatus().getStatus())
				.creationDateTime(subscription.getCreationDateTime())
				.lastRenewalDateTime(subscription.getLastRenewalDateTime())
				.nextRenewalDate(subscription.getNextRenewalDate())
				.paymentMethod(subscription.getPaymentMethod().getMethod())
				.subtotalPrice(subscription.getSubtotalPrice())
				.billingName(subscription.getBillingName())
				.billingAddress(subscription.getBillingAddress())
				.billingEmail(subscription.getBillingEmail())
				.billingCompanyName(subscription.getBillingCompanyName())
				.billingMobileNumber(subscription.getBillingMobileNumber())
				.items(subscription.getItems())
				.build();
	}

}
