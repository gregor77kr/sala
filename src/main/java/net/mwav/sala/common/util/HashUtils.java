package net.mwav.sala.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HashUtils {

	public String digest(String algorithm, String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algorithm);

		md.update(text.getBytes(StandardCharsets.UTF_8));
		return toHexString(md.digest());
	}

	public String getSalt() {
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

	public boolean compare(String algorithm, String text, String digested) throws NoSuchAlgorithmException {
		return digested.equals(digest(algorithm, text));
	}

}
