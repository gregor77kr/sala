package net.mwav.sala.security.handler;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NoPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	// TO NOT USE spring security password encoding
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return true;
	}

}
