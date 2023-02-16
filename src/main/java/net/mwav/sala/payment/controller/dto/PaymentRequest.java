package net.mwav.sala.payment.controller.dto;

import java.io.Serializable;

import net.mwav.sala.payment.provider.dto.BillingKeyRequest;

public interface PaymentRequest extends Serializable {

	BillingKeyRequest toBillingKey();

}
