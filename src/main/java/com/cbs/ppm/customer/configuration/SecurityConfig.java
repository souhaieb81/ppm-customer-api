package com.cbs.ppm.customer.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${security.secrets.public-key}")
	private String publicKeyFilePath;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource))
				.authorizeHttpRequests(authorization -> authorization
						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated())
				.oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()));

		return http.build();
	}

	@Bean
	public JwtDecoder jwtDecoder() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		RSAPublicKey publicKey = getPublicKey();
		return NimbusJwtDecoder.withPublicKey(publicKey).build();
	}
	
	private RSAPublicKey getPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
	    // Load and log key content
	    String publicKeyContent = new String(Files.readAllBytes(Paths.get(publicKeyFilePath)))
	            .replace("-----BEGIN PUBLIC KEY-----", "")
	            .replace("-----END PUBLIC KEY-----", "")
	            .replaceAll("\\s", "");

	    byte[] keyBytes = java.util.Base64.getDecoder().decode(publicKeyContent);
	    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    return (RSAPublicKey) keyFactory.generatePublic(spec);
	}
}
