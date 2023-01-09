package net.mwav.sala.authentication.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import net.mwav.sala.common.dto.ExceptionDetail;
import net.mwav.sala.common.dto.ExceptionResponseBody;
import net.mwav.sala.common.util.JsonUtils;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	
	// occurs when request to unauthorized resource
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

		ExceptionDetail exceptionDetail = ExceptionDetail.builder()
				.code(HttpStatus.FORBIDDEN.value())
				.type(HttpStatus.FORBIDDEN.series())
				.detail(HttpStatus.FORBIDDEN.getReasonPhrase())
				.build();

		ExceptionResponseBody<?> exception = ExceptionResponseBody.create(exceptionDetail);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
		response.setStatus(HttpStatus.FORBIDDEN.value());
		PrintWriter writer = response.getWriter();
		writer.print(JsonUtils.convertToJson(exception));
		writer.flush();
		
	}
}
