package net.mwav.sala.subscription.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.global.util.RandomUtils;
import net.mwav.sala.product.entity.constant.Currency;
import net.mwav.sala.subscription.entity.constant.PaymentMethod;
import net.mwav.sala.subscription.entity.constant.PaymentPeriod;
import net.mwav.sala.subscription.entity.constant.SubscriptionStatus;

@Entity
@Table(name = "subscription")
@Builder
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
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "subscription_no")
	private String no;

	@Column(name = "subscription_status")
	@Enumerated(EnumType.STRING)
	@Setter
	private SubscriptionStatus subscriptionStatus;

	@Column(name = "creation_date_time")
	@Builder.Default
	private LocalDateTime creationDateTime = LocalDateTime.now();

	@Column(name = "expiry_date_time")
	@Builder.Default
	private LocalDateTime expiryDateTime = LocalDateTime.of(9999, 12, 31, 0, 0);

	@Column(name = "last_renewal_date_time")
	@Builder.Default
	@Setter
	private LocalDateTime lastRenewalDateTime = LocalDateTime.now();

	@Column(name = "next_renewal_date")
	@Setter
	private LocalDate nextRenewalDate;

	@Column(name = "next_notification_date")
	@Setter
	private LocalDate nextNotificationDate;

	@Column(name = "payment_period")
	@Enumerated(EnumType.STRING)
	@Setter
	private PaymentPeriod paymentPeriod;

	@Column(name = "payment_method")
	@Enumerated(EnumType.STRING)
	@Setter
	private PaymentMethod paymentMethod;

	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	@Setter
	private Currency currency;

	@Column(name = "subtotal_price")
	@Builder.Default
	private double subtotalPrice = 0;

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

	@OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SubscriptionItem> items;

	// generate uuid when no data is null
	public void generateNo() {
		this.no = (this.no == null) ? RandomUtils.generateUUID() : this.no;
	}

	// calculate a subtotal and total price of each items
	public void calculatePrice() {
		calculateTotalItemPrice();
		calculateSubtotalPrice();
	}

	public void computeNextPeriod() {
		if (this.paymentPeriod != null) {
			LocalDate nextRenewalDate = LocalDate.now()
					.plusMonths((this.paymentPeriod == PaymentPeriod.MONTHLY) ? 1 : 12);

			this.nextNotificationDate = nextRenewalDate.plusDays(-5);
			this.nextRenewalDate = nextRenewalDate;
		}
	}

	// synchronize price 
	public void synchronizePrice() {
		if (this.items != null) {
			this.items.stream().forEach(i -> i.synchronizePrice());
		}

		// recalculate a price after synchronizing
		calculatePrice();
	}

	// calculate an item price of each item
	private void calculateTotalItemPrice() {
		if (this.items != null) {
			this.items.stream().forEach(i -> i.calculateTotalItemPrice());
		}
	}

	// calculate a subtotal
	private void calculateSubtotalPrice() {
		this.subtotalPrice = (this.items == null) ? 0
				: this.items.stream().mapToDouble(i -> i.getTotalItemPrice()).sum();
	}

	public void addItems(List<SubscriptionItem> items) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}

		this.items.addAll(items);
		this.items.forEach(i -> {
			i.setSubscription(this);
		});
	}

	public void onCreate() {
		generateNo();
		setSubscriptionStatus(SubscriptionStatus.CREATED);
		computeNextPeriod();
		synchronizePrice();
	}

	// convert subscription entity to order entity
	public SubscriptionOrder toOrder() {
		SubscriptionOrder subscriptionOrder = SubscriptionOrder.builder()
				.customer(this.customer)
				.subscription(this)
				.paymentPeriod(this.paymentPeriod)
				.paymentMethod(this.paymentMethod)
				.currency(this.currency)
				.subtotalPrice(this.subtotalPrice)
				.build();

		List<SubscriptionOrderItem> subscriptionOrderItems = this.items.stream().map(i -> {
			SubscriptionOrderItem item = SubscriptionOrderItem.builder()
					.product(i.getProduct())
					.quantity(i.getQuantity())
					.price(i.getPrice())
					.totalItemPrice(i.getTotalItemPrice())
					.build();
			return item;
		}).collect(Collectors.toList());

		subscriptionOrder.addItems(subscriptionOrderItems);
		return subscriptionOrder;
	}

}
