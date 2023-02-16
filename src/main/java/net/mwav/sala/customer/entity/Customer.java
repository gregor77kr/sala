package net.mwav.sala.customer.entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import lombok.Setter;
import lombok.ToString;
import net.mwav.sala.global.util.HashUtils;

@Entity
@Table(name = "customer")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@EqualsAndHashCode
public class Customer implements Serializable {

	private static final long serialVersionUID = -2326242046269826943L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "password")
	private String password;

	@Column(name = "salt")
	private String salt;

	@Column(name = "email")
	private String email;

	@Builder.Default
	@Column(name = "is_enabled")
	private boolean isEnabled = true;

	@Column(name = "is_verified")
	@Setter
	private boolean isVerified;

	public void digestPassword() throws NoSuchAlgorithmException {
		this.salt = HashUtils.getSalt();
		this.password = HashUtils.digest("SHA-256", this.password + this.salt);
	}

}
