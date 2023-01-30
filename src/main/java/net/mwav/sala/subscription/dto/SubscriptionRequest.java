package net.mwav.sala.subscription.dto;

import java.io.Serializable;

import lombok.Value;
import net.mwav.sala.subscription.entity.Subscription;

@Value
public class SubscriptionRequest implements Serializable {

	private static final long serialVersionUID = -5276218624939209139L;

	public static Subscription toEntity() {
		return Subscription.builder(null)
				.build();
	}

}
