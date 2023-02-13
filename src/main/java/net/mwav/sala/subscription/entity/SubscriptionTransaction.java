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
import lombok.Setter;
import lombok.ToString;
import net.mwav.sala.customer.entity.Customer;
import net.mwav.sala.global.constant.TransactionStatus;

@Entity
@Table(name = "subscription_transaction")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class SubscriptionTransaction implements Serializable {

	private static final long serialVersionUID = 6304912106630716160L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscription_transaction_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_order_id")
	private SubscriptionOrder subscriptionOrder;

	@Column(name = "transaction_status")
	@Enumerated(EnumType.STRING)
	@Setter
	private TransactionStatus transactionStatus;

	@Column(name = "transaction_date_time")
	@Builder.Default
	private LocalDateTime transactionDateTime = LocalDateTime.now();

	@Column(name = "payment_date_time")
	@Setter
	private LocalDateTime paymentDateTime;

}
