package net.mwav.sala.security.handler;

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

import net.mwav.sala.global.model.ExceptionDetail;
import net.mwav.sala.global.model.ExceptionResponseBody;
import net.mwav.sala.global.util.JsonUtils;

@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {
	
	// occurs when request to unauthorized resource
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

		ExceptionDetail exceptionDetail = ExceptionDetail.builder()
				.code(HttpStatus.FORBIDDEN.value())
				.type(HttpStatus.FORBIDDEN.series().name())
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
