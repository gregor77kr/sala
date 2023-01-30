package net.mwav.sala.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.authentication.jwt.JwtFilter;
import net.mwav.sala.authentication.jwt.JwtTokenProvider;
import net.mwav.sala.authentication.jwt.JwtWebResolver;
import net.mwav.sala.security.impl.JsonAccessDeniedHandler;
import net.mwav.sala.security.impl.JsonAuthenticationEntryPoint;
import net.mwav.sala.security.impl.NoPasswordEncoder;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtTokenProvider jwtTokenService;

	private final JwtWebResolver jwtWebResolver;

	private final CorsFilter corsFilter;

	private final JsonAuthenticationEntryPoint jsonAuthenticationEntryPoint;

	private final JsonAccessDeniedHandler jsonAccessDeniedHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new NoPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()

			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

			.exceptionHandling()
			.authenticationEntryPoint(jsonAuthenticationEntryPoint)
			.accessDeniedHandler(jsonAccessDeniedHandler)

			// enable h2-console
			.and()
			.headers()
			.frameOptions()
			.sameOrigin()

			// 세션을 사용하지 않기 때문에 STATELESS로 설정
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
			.authorizeHttpRequests()
			.antMatchers("/api/customers", "/api/authentication/token")
			.permitAll()
			.requestMatchers(PathRequest.toH2Console())
			.permitAll()
			.anyRequest()
			.authenticated()

			.and()
			.addFilterBefore(new JwtFilter(jwtTokenService, jwtWebResolver), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
