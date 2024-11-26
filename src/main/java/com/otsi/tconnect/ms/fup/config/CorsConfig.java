package com.otsi.tconnect.ms.fup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Content-Length, Authorization, credential, X-XSRF-TOKEN,keepalive-time,keepalive,storeId,clientId";
	private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, PATCH";
	private static final String ALLOWED_ORIGIN = "*";
	private static final String MAX_AGE = "7200";

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(ALLOWED_ORIGIN);
		config.addAllowedHeader(ALLOWED_HEADERS);
		config.addAllowedMethod(ALLOWED_METHODS);
		// config.setMaxAge(MAX_AGE);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
