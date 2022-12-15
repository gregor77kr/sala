package net.mwav.sala.customer.entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.mwav.sala.common.util.HashUtils;

@Entity
@Table(name = "customer")
@Builder(builderMethodName = "customerBuilder")
@Getter
@ToString
@EqualsAndHashCode
public class Customer implements Serializable {

	private static final long serialVersionUID = -2326242046269826943L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;

	@Column(name = "customer_name")
	private String name;

	@Column(name = "customer_fullname")
	private String fullname;

	@Column(name = "password")
	private String password;

	@Column(name = "salt")
	private String salt;

	@Column(name = "email")
	private String email;

	@Builder.Default
	@Column(name = "is_enabled")
	private boolean is_enabled = true;

	@Column(name = "authentication_code")
	private String authentication_code;

	@Column(name = "is_authenticated")
	private boolean is_authenticated;

	public void digestPassword() throws NoSuchAlgorithmException {
		this.salt = HashUtils.getSalt();
		this.password = HashUtils.digest("SHA-256", this.password + this.salt);
	}

	public void generateCode() {

	}

	private String getSalt() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return toHexString(salt);
	}

	private String toHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();

		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

	public static CustomerBuilder builder(String name) {
		return customerBuilder().name(name);
	}
}
