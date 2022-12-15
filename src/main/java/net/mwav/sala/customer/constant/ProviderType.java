package net.mwav.sala.customer.constant;

import lombok.Getter;

@Getter
public enum ProviderType {

	GOOGLE("GOOGLE"),
	NAVER("NAVER"),
	KAKAO("KAKAO");

	private final String type;

	ProviderType(String type) {
		this.type = type;
	}
}