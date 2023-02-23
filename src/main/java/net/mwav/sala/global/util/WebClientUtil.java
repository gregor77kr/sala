package net.mwav.sala.global.util;

import javax.net.ssl.SSLException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.experimental.UtilityClass;
import reactor.netty.http.client.HttpClient;

@UtilityClass
public class WebClientUtil {

	public static WebClient createClient() {
		return defaultClient(defaultExchangeStrategies(), defaultHttpClient());
	}

	public static WebClient defaultClient(ExchangeStrategies exchangeStrategies, HttpClient httpClient) {
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.defaultHeaders(header -> {
					header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
					header.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
				})
				.exchangeStrategies(exchangeStrategies)
				.build();
	}

	public static HttpClient defaultHttpClient() {
		return HttpClient.create()
				.secure(s -> {
					try {
						s.sslContext(SslContextBuilder
								.forClient()
								.trustManager(InsecureTrustManagerFactory.INSTANCE)
								.build());
					} catch (SSLException e) {
						e.printStackTrace();
					}
				})
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
				.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(10))
						.addHandlerLast(new WriteTimeoutHandler(10)));
	}

	public static ExchangeStrategies defaultExchangeStrategies() {
		final ObjectMapper OM = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.registerModule(new JavaTimeModule());

		return ExchangeStrategies.builder().codecs(config -> {
			config.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(OM, MediaType.APPLICATION_JSON));
			config.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(OM, MediaType.APPLICATION_JSON));
			config.defaultCodecs().maxInMemorySize(1024 * 1024);
		}).build();
	}

}
