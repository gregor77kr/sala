package net.mwav.sala.subscription.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.mwav.sala.subscription.entity.Subscription;

@NoArgsConstructor
@Data
public class SubscriptionRequest implements Serializable {

	private static final long serialVersionUID = -5276218624939209139L;

	public static Subscription toEntity() {
		return Subscription.builder(null)
				.build();
	}

}
