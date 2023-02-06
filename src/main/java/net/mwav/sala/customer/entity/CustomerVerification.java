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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.mwav.sala.common.exception.ExpiryException;
import net.mwav.sala.common.util.RandomUtils;

@Entity
@Table(name = "customer_verification")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class CustomerVerification implements Serializable {

	private static final long serialVersionUID = -2758293565542858708L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_verification_id")
	private long customerAuthId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "verification_code")
	private String verificationCode;

	@Column(name = "creation_date_time")
	private LocalDateTime creationDateTime;

	@Column(name = "expiry_date_time")
	private LocalDateTime expiryDateTime;

	public static CustomerVerification create(Customer customer) {
		CustomerVerification customerVerification = CustomerVerification.builder().customer(customer).build();
		customerVerification.setAuthenticationRequest();

		return customerVerification;
	}

	public void setAuthenticationRequest() {
		this.verificationCode = RandomUtils.generateNumber(6);
		this.creationDateTime = LocalDateTime.now();
		this.expiryDateTime = LocalDateTime.now().plusDays(1);
	}

	public void verify(String verificationCode) throws ExpiryException {
		if (!isValidInTime()) {
			throw new ExpiryException();
		}

		// TODO : need to replace RuntimeException to custom excpetion
		if (!this.verificationCode.equals(verificationCode)) {
			throw new RuntimeException();
		}

		this.expiryDateTime = LocalDateTime.now();
		this.customer.setVerified(true);
	}

	private boolean isValidInTime() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime creationDate = this.creationDateTime;
		LocalDateTime expiryDate = this.expiryDateTime;

		if (now.isAfter(expiryDate) || now.isBefore(creationDate)) {
			return false;
		}

		return true;
	}

}
