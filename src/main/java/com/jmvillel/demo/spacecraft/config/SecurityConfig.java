package com.jmvillel.demo.spacecraft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(AbstractHttpConfigurer::disable);
	    http.authorizeHttpRequests(request -> {
	    		request.requestMatchers("/apidocs/**","/v3/api-docs/**","/swagger-ui/index.html", "/swagger-ui/**").permitAll();
	            request.requestMatchers("/api/**").authenticated();
	        });
		http.httpBasic(Customizer.withDefaults());

		return http.build();
	}

}
