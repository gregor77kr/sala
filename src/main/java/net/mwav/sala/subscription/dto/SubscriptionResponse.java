package net.mwav.sala.subscription.dto;

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

    private long id;

    private String no;

    private String status;

    private LocalDateTime creationDateTime;

    private LocalDateTime expiryDateTime;

    private LocalDateTime lastRenewalDateTime;

    private LocalDate nextRenewalDate;

    private LocalDate nextInvoiceDate;

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
                .expiryDateTime(subscription.getExpiryDateTime())
                .lastRenewalDateTime(subscription.getLastRenewalDateTime())
                .nextRenewalDate(subscription.getNextRenewalDate())
                .nextInvoiceDate(subscription.getNextInvoiceDate())
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
