package net.mwav.sala.customer.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OauthProviderType {

	GOOGLE("GOOGLE"),
	NAVER("NAVER"),
	KAKAO("KAKAO");

	private final String type;

}
