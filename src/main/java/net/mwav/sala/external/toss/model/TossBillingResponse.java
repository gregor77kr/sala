package net.mwav.sala.external.toss.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class TossBillingResponse implements Serializable {

	private static final long serialVersionUID = -7457981549052368484L;

	private String version;

	private String paymentKey;

	private String type;

	private String orderId;

	private String orderName;

	private String mId;

	private String currency;

	private String method;

	private int totalAmount;

	// cancelable amount
	private int balanceAmount;

	private int suppliedAmount;

	private int taxFreeAmount;

	// ROUND((amount - taxFreeAmount) / 11, 1)
	private int vat;

	private String status;

	private String requestAt;

	private String approvedAt;

	private boolean useEscrow;

	private String lastTransactionKey;

}
