package net.mwav.sala.subscription.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import net.mwav.sala.global.constant.Currency;
import net.mwav.sala.global.constant.OrderStatus;
import net.mwav.sala.global.constant.PaymentMethod;
import net.mwav.sala.global.constant.PaymentPeriod;
import net.mwav.sala.global.constant.TransactionStatus;
import net.mwav.sala.global.util.RandomUtils;

/**
 * This entity is a copy of subscription entity at the moment of creation.
 *
 */
@Entity
@Table(name = "subscription_order")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class SubscriptionOrder implements Serializable {

	private static final long serialVersionUID = 3398934038310655259L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscription_order_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	@Column(name = "subscription_order_no")
	private String no;

	@Column(name = "order_status")
	@Enumerated(EnumType.STRING)
	@Setter
	private OrderStatus orderStatus;

	@Column(name = "order_date_time")
	@Builder.Default
	private LocalDateTime orderDateTime = LocalDateTime.now();

	@Column(name = "payment_period")
	@Enumerated(EnumType.STRING)
	private PaymentPeriod paymentPeriod;

	@Column(name = "payment_method")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	@Column(name = "period_start_date")
	@Setter
	private LocalDate periodStartDate;

	@Column(name = "period_end_date")
	@Setter
	private LocalDate periodEndDate;

	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@Column(name = "subtotal_price")
	@Builder.Default
	private double subtotalPrice = 0;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SubscriptionOrderItem> items;

	// generate uuid when no data is null
	public void generateNo() {
		this.no = (this.no == null) ? RandomUtils.generateUUID() : this.no;
	}

	public void calculatePeriod() {
		if (this.paymentPeriod != null) {
			LocalDate stardDate = LocalDate.now();
			LocalDate endDate = LocalDate.now().plusMonths((this.paymentPeriod == PaymentPeriod.MONTHLY) ? 1 : 12);

			this.periodStartDate = stardDate;
			this.periodEndDate = endDate;
		}
	}

	public void addItems(List<SubscriptionOrderItem> items) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}

		this.items.addAll(items);
		this.items.forEach(i -> {
			i.setOrder(this);
		});
	}

	public void onCreate() {
		generateNo();
		setOrderStatus(OrderStatus.CREATED);
		calculatePeriod();
	}

	public SubscriptionTransaction toTransaction() {
		SubscriptionTransaction subscriptionTransaction = SubscriptionTransaction.builder()
				.customer(this.customer)
				.transactionStatus(TransactionStatus.PENDING)
				.subscriptionOrder(this)
				.build();

		return subscriptionTransaction;
	}

}
