package net.mwav.sala.payment.provider.dto;

import java.io.Serializable;

import net.mwav.sala.payment.entity.Payment;
import net.mwav.sala.payment.provider.constant.PaymentProviderType;

/**
 * Marker interface for billing key request
 *
 */
public interface BillingKeyRequest extends Serializable {

	public static BillingKeyRequest from(Payment payment) {
		BillingKeyRequest billingKeyRequest;

		if (payment.getProviderType() == PaymentProviderType.TOSS) {
			billingKeyRequest = TossBillingKeyRequest.from(payment);
		} else {
			billingKeyRequest = TossBillingKeyRequest.from(payment);
		}

		return billingKeyRequest;
	}

}