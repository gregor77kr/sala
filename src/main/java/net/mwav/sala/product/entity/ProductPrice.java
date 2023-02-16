package net.mwav.sala.product.entity;

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
import net.mwav.sala.product.entity.constant.Currency;

@Entity
@Table(name = "product_price")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString(exclude = "product")
@EqualsAndHashCode
public class ProductPrice implements Serializable {
	
	private static final long serialVersionUID = -7212449753810341145L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_price_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "monthly_price")
    private double monthlyPrice;
    
    @Column(name = "annual_price")
    private double annualPrice;

    @Column(name = "is_active")
    @Builder.Default
    private boolean isActive = true;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "expiry_date_time")
    private LocalDateTime expiryDateTime;
    
    public boolean matches(Currency currency) {
    	return (this.currency == currency) & isValidInTime();
    }

    public boolean isValidInTime() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(this.expiryDateTime) || now.isBefore(creationDateTime)) {
            return false;
        }

        return (this.isActive == true);
    }
}
