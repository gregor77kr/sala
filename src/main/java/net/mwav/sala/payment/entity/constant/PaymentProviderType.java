package net.mwav.sala.payment.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentProviderType {

	TOSS("TOSS"),
	BOOTPAY("BOOTPAY");

	private final String provider;

}
