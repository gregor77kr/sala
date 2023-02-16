package net.mwav.sala.subscription.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.mwav.sala.product.entity.Product;
import net.mwav.sala.subscription.entity.constant.PaymentPeriod;

@Entity
@Table(name = "subscription_item")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString(exclude = { "subscription", "product" })
@EqualsAndHashCode
public class SubscriptionItem implements Serializable {

	private static final long serialVersionUID = 4547890474116915296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subscription_item_id")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscription_id")
	@JsonBackReference
	private Subscription subscription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@Setter
	@JsonBackReference
	private Product product;

	@Column(name = "price")
	private double price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "total_item_price")
	private double totalItemPrice;

	// override setter for subscription field(only set when subsription is null)
	public void setSubscription(Subscription subscription) {
		this.subscription = (this.subscription == null) ? subscription : this.subscription;
	}

	// calculate item price
	public void calculateTotalItemPrice() {
		this.totalItemPrice = this.price * this.quantity;
	}

	// synchronize price in Product and Item
	public void synchronizePrice() {
		if (this.product == null) {
			this.price = 0;
		} else {
			this.price = (this.getSubscription().getPaymentPeriod() == PaymentPeriod.MONTHLY)
					? this.product.getMonthlyPrice(this.subscription.getCurrency())
					: this.product.getAnnualPrice(this.subscription.getCurrency());
		}

	}

}
