package net.mwav.sala.common.config;

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
import net.mwav.sala.authentication.jwt.JwtAccessDeniedHandler;
import net.mwav.sala.authentication.jwt.JwtAuthenticationEntryPoint;
import net.mwav.sala.authentication.jwt.JwtFilter;
import net.mwav.sala.authentication.jwt.TokenProvider;
import net.mwav.sala.authentication.service.NoPasswordEncoder;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final TokenProvider tokenProvider;

	private final CorsFilter corsFilter;

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
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
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler)

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
			.antMatchers("/customers", "/customers/authentication", "*.css", "*.gif", "/favicon.ico")
			.permitAll()
			.requestMatchers(PathRequest.toH2Console())
			.permitAll()
			.anyRequest()
			.authenticated()

			.and()
			.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

}
