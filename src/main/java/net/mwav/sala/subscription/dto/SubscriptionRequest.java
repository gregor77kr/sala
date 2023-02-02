package net.mwav.sala.subscription.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Value;
import net.mwav.sala.common.constant.PaymentMethod;
import net.mwav.sala.common.constant.PaymentPeriod;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.subscription.entity.Subscription;

@Value
public class SubscriptionRequest implements Serializable {

	private static final long serialVersionUID = -5276218624939209139L;

	@NotNull
	private long customerId;

	@NotBlank
	private String paymentPeriod;

	@NotBlank
	private String paymentMethod;

	@NotBlank
	private String billingName;

	@NotBlank
	private String billingAddress;

	@NotBlank
	@Email
	@Size(max = 256)
	private String billingEmail;

	@NotBlank
	private String billingCompanyName;

	@NotBlank
	private String billingMobileNumber;

	@NotNull
	private List<?> items;

	public Subscription toEntity() {
		Customer customer = Customer.builder(null).id(customerId).build();

		return Subscription.builder(customer)
				.paymentPeriod(PaymentPeriod.valueOf(paymentPeriod))
				.paymentMethod(PaymentMethod.valueOf(paymentMethod))
				.billingName(billingName)
				.billingAddress(billingAddress)
				.billingEmail(billingEmail)
				.billingCompanyName(billingCompanyName)
				.billingMobileNumber(billingMobileNumber)
				.build();
	}

}
