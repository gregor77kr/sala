package net.mwav.sala.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProviderType {

	GOOGLE("GOOGLE"),
	NAVER("NAVER"),
	KAKAO("KAKAO");

	private final String type;

}
