package net.mwav.sala.common.constant;

import lombok.Getter;

@Getter
public enum Currency {

	KRW("KRW"),
	EUR("EUR"),
	USD("USD"),
	JPY("JPY");

	private final String currency;

	Currency(String currency) {
		this.currency = currency;
	}
}
