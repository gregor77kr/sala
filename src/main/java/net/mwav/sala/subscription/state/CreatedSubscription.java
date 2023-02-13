package net.mwav.sala.subscription.state;

import net.mwav.sala.global.constant.SubscriptionStatus;
import net.mwav.sala.subscription.entity.Subscription;

public class CreatedSubscription implements SubscriptionState {

	@Override
	public void change(Subscription subscription) {
		subscription.generateNo();
		subscription.setSubscriptionStatus(SubscriptionStatus.CREATED);
		subscription.calculateNextPeriod();
		subscription.synchronizePrice();
	}

}
