package net.mwav.sala.customer.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.mwav.sala.common.util.RandomUtils;

@Entity
@Table(name = "customer_auth")
@Builder(builderMethodName = "customerAuthBuilder")
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
		CustomerAuth customerAuth = CustomerAuth.builder(customer)
				.authenticationCode(RandomUtils.generateNumber(6))
				.creationDate(LocalDateTime.now())
				.expiryDate(LocalDateTime.now().plusDays(1))
				.build();

		return customerAuth;
	}

	public void setAuthenticationRequest() {
		this.authenticationCode = RandomUtils.generateNumber(6);
		this.creationDate = LocalDateTime.now();
		this.expiryDate = LocalDateTime.now().plusDays(1);
	}

	public static CustomerAuthBuilder builder(Customer customer) {
		return customerAuthBuilder().customer(customer);
	}
}
