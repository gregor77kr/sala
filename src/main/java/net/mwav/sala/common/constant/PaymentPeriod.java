package net.mwav.sala.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentPeriod {

	MONTHLY(1), ANNUAL(12);

	private final int period;

}
