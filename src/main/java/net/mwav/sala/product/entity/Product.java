package net.mwav.sala.product.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.mwav.sala.common.constant.Currency;
import net.mwav.sala.common.constant.PaymentPeriod;

@Entity
@Table(name = "product")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class Product implements Serializable {

	private static final long serialVersionUID = 7704943995042716440L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "is_active")
	@Builder.Default
	private boolean isActive = true;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductPrice> prices;

	public double getPrice(PaymentPeriod paymentPeriod, Currency currency) {
		if (this.prices == null) {
			return 0;
		}

		 ProductPrice productPrice = this.prices.stream().filter(p -> {
			return p.matches(currency);
		}).findFirst().orElseThrow(EntityNotFoundException::new);
		
		 return (paymentPeriod == PaymentPeriod.MONTHLY) ? productPrice.getMontlyPrice() : productPrice.getAnnualPrice();
	}
}
