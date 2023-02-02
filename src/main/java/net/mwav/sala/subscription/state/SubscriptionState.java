package net.mwav.sala.subscription.state;

import net.mwav.sala.subscription.entity.Subscription;

/**
 * Interface defining the behaviour of {@Subscription} associated with {@SubscriptionStatus}
 */
public interface SubscriptionState {
    
    public void set(Subscription t);

}
