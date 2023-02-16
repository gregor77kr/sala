package net.mwav.sala.payment.provider.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentProviderType {

	TOSS("TOSS");

	private final String provider;

}