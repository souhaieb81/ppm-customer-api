package com.cbs.ppm.customer.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/v3/api-docs/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*");
		registry.addMapping("/swagger-ui/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*");
	}
}
