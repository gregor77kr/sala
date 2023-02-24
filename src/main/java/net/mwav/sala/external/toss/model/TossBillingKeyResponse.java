package net.mwav.sala.external.toss.model;

import java.io.Serializable;

import org.springframework.web.reactive.function.client.WebClient;

import lombok.Data;

/**
 * Could not create immutable dto in {@link WebClient.ResponseSpec#bodyToMono(Class)}.
 *
 */
@Data
public class TossBillingKeyResponse implements Serializable {

	private static final long serialVersionUID = 4449024050936841591L;

	private String mId;

	private String customerKey;

	private String authenticatedAt;

	private String method;

	private String billingKey;

	//	@JsonCreator
	//	public TossBillingKeyResponse(@JsonProperty String mId,
	//			@JsonProperty String customerKey,
	//			@JsonProperty String authenticatedAt,
	//			@JsonProperty String method,
	//			@JsonProperty String billingKey) {
	//
	//		this.mId = mId;
	//		this.customerKey = customerKey;
	//		this.authenticatedAt = authenticatedAt;
	//		this.method = method;
	//		this.billingKey = billingKey;
	//	}

}
