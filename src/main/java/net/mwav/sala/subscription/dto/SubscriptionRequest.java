package net.mwav.sala.subscription.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import net.mwav.sala.subscription.entity.Subscription;

@Data
@Builder
public class SubscriptionRequest implements Serializable {

	private static final long serialVersionUID = -5276218624939209139L;

	public static Subscription toEntity() {
		return Subscription.builder(null)
				.build();
	}

}
