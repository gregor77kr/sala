package net.mwav.sala.common.util;

import java.util.Random;
import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtils {

	public String generateNumber(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(9));
		}

		return sb.toString();
	}

	public String generateUUID() {
		return UUID.randomUUID().toString();
	}
}
