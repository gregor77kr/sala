package net.mwav.sala.customer.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import net.mwav.sala.common.util.RandomUtils;

@Entity
@Table(name = "customer_auth")
@Builder(builderMethodName = "customerAuthBuilder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class CustomerAuth implements Serializable {

	private static final long serialVersionUID = -2758293565542858708L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_auth_id")
	private long customerAuthId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "authentication_code")
	private String authenticationCode;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;

	public static CustomerAuth create(Customer customer) {
		CustomerAuth customerAuth = CustomerAuth.builder(customer).build();
		customerAuth.setAuthenticationRequest();

		return customerAuth;
	}

	public void setAuthenticationRequest() {
		this.authenticationCode = RandomUtils.generateNumber(6);
		this.creationDate = LocalDateTime.now();
		this.expiryDate = LocalDateTime.now().plusDays(1);
	}

	public boolean isValid() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime creationDate = this.creationDate;
		LocalDateTime expiryDate = this.expiryDate;

		if (now.isAfter(expiryDate) || now.isBefore(creationDate)) {
			return false;
		}

		return true;
	}

	public CustomerAuth ifValid(Consumer<CustomerAuth> consumer) {
		if (this.isValid()) {
			consumer.accept(this);
		}

		return this;
	}

	public <X extends Throwable> void orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
		if (!isValid()) {
			exceptionSupplier.get();
		}
	}

	public void authenticate() {
		this.expiryDate = LocalDateTime.now();
		this.customer.setAuthenticated(true);
	}

	public static CustomerAuthBuilder builder(Customer customer) {
		return customerAuthBuilder().customer(customer);
	}
}
