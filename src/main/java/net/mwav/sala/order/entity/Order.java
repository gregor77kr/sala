package net.mwav.sala.order.entity;

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
import net.mwav.sala.global.util.RandomUtils;
import net.mwav.sala.order.state.OrderState;
import net.mwav.sala.subscription.entity.Subscription;

@Entity
@Table(name = "order_list")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class Order implements Serializable {

	private static final long serialVersionUID = 3398934038310655259L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	@Column(name = "order_no")
	private String no;

	@Column(name = "order_status")
	@Enumerated(EnumType.STRING)
	@Setter
	private OrderStatus orderStatus;

	@Column(name = "order_date")
	@Setter
	private LocalDateTime orderDate;

	@Column(name = "payment_period")
	@Enumerated(EnumType.STRING)
	@Setter
	private PaymentPeriod paymentPeriod;

	@Column(name = "payment_method")
	@Enumerated(EnumType.STRING)
	@Setter
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

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderItem> items;
	
	// delegate data handling process to OrderState
	public void changeState(OrderState orderState) {
		orderState.change(this);
	}
	
	// generate uuid when no data is null
	public void generateNo() {
		this.no = (this.no == null) ? RandomUtils.generateUUID() : this.no;
	}

	public void addItems(List<OrderItem> items) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}

		this.items.addAll(items);
		this.items.forEach(i -> {
			i.setOrder(this);
		});
	}

}
