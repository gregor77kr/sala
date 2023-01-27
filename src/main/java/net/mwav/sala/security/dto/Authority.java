package net.mwav.sala.security.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.ObjectUtils;

public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 8384627639568468060L;

	private String authority;

	public Authority(String authority) {
		this.authority = authority;
	}

	public Authority(Map<String, ?> authority) {
		if (!ObjectUtils.isEmpty(authority)) {
			this.authority = String.valueOf(authority.get("authority"));
		}
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public static List<Authority> map(List<Map<String, ?>> data) {
		return data.stream()
				.map(d -> {
					return new Authority(d);
				})
				.collect(Collectors.toList());
	}
}
