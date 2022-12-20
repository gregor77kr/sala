package net.mwav.sala.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.mwav.sala.customer.constant.ProviderType;

@Entity
@Table(name = "customer_oauth")
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CustomerOAuth implements Serializable {

	private static final long serialVersionUID = -6562869913113393712L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_oauth_id")
	private long id;

	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "provider_type")
	@Enumerated(EnumType.STRING)
	private ProviderType providerType;

	@Column(name = "provider_id")
	private String ProviderId;

}
