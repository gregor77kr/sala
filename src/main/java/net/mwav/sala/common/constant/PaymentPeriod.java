package net.mwav.sala.common.constant;

public enum PaymentPeriod {

	MONTHLY(1), ANNUAL(12);

	private final int period;

	private PaymentPeriod(int period) {
		this.period = period;
	}

	public int getPeriod() {
		return this.period;
	}

}
