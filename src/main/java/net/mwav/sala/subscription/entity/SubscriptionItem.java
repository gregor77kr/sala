package net.mwav.sala.subscription.entity;

import java.io.Serializable;

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
import net.mwav.sala.common.constant.Currency;

@Entity
@Table(name = "subscription_item")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString(exclude = "subscription")
@EqualsAndHashCode
public class SubscriptionItem implements Serializable {

	private static final long serialVersionUID = 4547890474116915296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscription_item_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@Column(name = "price")
	private double price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "item_price")
	private double itemPrice;

	public void setSubscription(Subscription subscription) {
		this.subscription = (this.subscription == null) ? subscription : this.subscription;
	}

	public void calculateItemPrice() {
		this.itemPrice = this.price * this.quantity;
	}
}
