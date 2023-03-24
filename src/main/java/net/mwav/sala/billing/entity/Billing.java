package net.mwav.sala.billing.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.mwav.sala.billing.entity.constant.BillingProviderType;

@Entity
@Table(name = "billing")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class Billing implements Serializable {

	private static final long serialVersionUID = 3092089347154966151L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billing_id")
	private Long id;

	@Column(name = "provider_type")
	@Enumerated(EnumType.STRING)
	private BillingProviderType providerType;

	@Column(name = "subscription_no")
	private String subscriptionNo;

	@Column(name = "billing_key")
	private String billingKey;

}
