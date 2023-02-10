package net.mwav.sala.subscription.state;

import java.time.LocalDate;
import java.time.LocalDateTime;

import net.mwav.sala.global.constant.PaymentPeriod;
import net.mwav.sala.global.constant.SubscriptionStatus;
import net.mwav.sala.subscription.entity.Subscription;

public class CreatedState implements SubscriptionState {

    @Override
    public void change(Subscription subscription) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate nextRenewalDate = LocalDate.now().plusMonths((subscription.getPaymentPeriod() == PaymentPeriod.MONTHLY) ? 1 : 12);
        LocalDate nextInvoiceDate = nextRenewalDate.plusDays(-5);
        
        subscription.generateNo();
        subscription.setSubscriptionStatus(SubscriptionStatus.CREATED);
        subscription.setCreationDateTime(now);
        subscription.setExpiryDateTime(LocalDateTime.of(9999, 12, 31, 0, 0));
        subscription.setLastRenewalDateTime(now);
        subscription.setNextInvoiceDate(nextInvoiceDate);
        subscription.setNextRenewalDate(nextRenewalDate);
        
        subscription.synchronizePrice();
    }

}
