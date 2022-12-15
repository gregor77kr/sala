package net.mwav.sala.common;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import net.mwav.sala.common.util.HashUtils;

@Slf4j
class HashTest {

	@Test
	void normalTest() throws Exception {
		String algorithm = "SHA-256";
		String password = "sala project key";

		String expected = "67b06aba2d472c85d04b9309c3de0714a238113b9f524fe0bdbc893198e3b5f0";
		String actual = HashUtils.digest(algorithm, password);
		log.debug(actual);

		assertEquals(expected, actual);
	}

	@Test
	void normalSaltTest() throws Exception {
		String algorithm = "SHA-256";
		String password = "sala project key";
		String salt = "430c9f8d549e97971e5a706c0c084e32";

		String expected = "078b0afbee41cc2a369932e11578281acba5352c9e45e4e38be17457e93de072";
		String actual = HashUtils.digest(algorithm, password + salt);
		log.debug(actual);

		assertEquals(expected, actual);
	}

	@Test
	void nullTest() throws Exception {
		String algorithm = "SHA-256";
		String password = "";

		String expected = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
		String actual = HashUtils.digest(algorithm, password);
		log.debug(actual);

		assertEquals(expected, actual);
	}

	@Test
	void otherTest() throws Exception {
		String algorithm = "MD5";
		String password = "sala project key";

		String expected = "ac31f4bd758d8eda3363336bdb3fd017";
		String actual = HashUtils.digest(algorithm, password);
		log.debug(actual);

		assertEquals(expected, actual);
	}

}
