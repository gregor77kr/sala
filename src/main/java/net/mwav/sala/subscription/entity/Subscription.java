package net.mwav.sala.subscription.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.mwav.sala.common.constant.PaymentMethod;
import net.mwav.sala.common.constant.PaymentPeriod;
import net.mwav.sala.common.constant.SubscriptionStatus;
import net.mwav.sala.common.util.RandomUtils;
import net.mwav.sala.customer.entity.Customer;

@Entity
@Table(name = "subscription")
@Builder(builderMethodName = "subscriptionBuilder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class Subscription implements Serializable {

	private static final long serialVersionUID = 3398934038310655259L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscription_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "subscription_no")
	private String no;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private SubscriptionStatus status;

	@Column(name = "creation_date_time")
	private LocalDateTime creationDateTime;

	@Column(name = "expiry_date_time")
	private LocalDateTime expiryDateTime;

	@Column(name = "last_renewal_date_time")
	private LocalDateTime lastRenewalDateTime;

	@Column(name = "next_renewal_date_time")
	private LocalDateTime nextRenewalDateTime;

	@Column(name = "next_invoice_date_time")
	private LocalDateTime nextInvoiceDateTime;

	@Column(name = "payment_period")
	@Enumerated(EnumType.STRING)
	private PaymentPeriod paymentPeriod;

	@Column(name = "payment_method")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	@Column(name = "subtotal_price")
	private int subtotalPrice;

	@Column(name = "billing_name")
	private String billingName;

	@Column(name = "billing_address")
	private String billingAddress;

	@Column(name = "billing_email")
	private String billingEmail;

	@Column(name = "billing_company_name")
	private String billingCompanyName;

	@Column(name = "billing_mobile_number")
	private String billingMobileNumber;

	public static SubscriptionBuilder builder(Customer customer) {
		return subscriptionBuilder().customer(customer).no(RandomUtils.generateUUID());
	}
	
}
