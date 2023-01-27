package net.mwav.sala.security.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import net.mwav.sala.common.dto.ExceptionDetail;
import net.mwav.sala.common.dto.ExceptionResponseBody;
import net.mwav.sala.common.util.JsonUtils;

@Component
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

	// occurs when requested not providing valid authentication
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

		ExceptionDetail exceptionDetail = ExceptionDetail.builder()
				.code(HttpStatus.UNAUTHORIZED.value())
				.type(HttpStatus.UNAUTHORIZED.series())
				.detail(HttpStatus.UNAUTHORIZED.getReasonPhrase())
				.build();

		ExceptionResponseBody<?> exception = ExceptionResponseBody.create(exceptionDetail);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		PrintWriter writer = response.getWriter();
		writer.print(JsonUtils.convertToJson(exception));
		writer.flush();

	}
}
