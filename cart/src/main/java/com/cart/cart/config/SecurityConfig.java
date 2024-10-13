package com.cart.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()  // Disable CSRF for simplicity (not recommended for production)
	        .authorizeHttpRequests((authz) -> authz
	            .requestMatchers(
	                // Swagger-related endpoints
	                "/v3/api-docs/**",     // OpenAPI documentation
	                "/swagger-ui/**",      // Swagger UI resources
	                "/swagger-ui.html",    // Swagger UI page
	                "/v3/api-docs.yaml",   // OpenAPI YAML documentation
	                "/v3/api-docs.json"    // OpenAPI JSON documentation
	            ).permitAll()  // Allow unrestricted access to Swagger
	         // Allow unrestricted access to specific item-related API
	            .requestMatchers("/api/items").permitAll()  // Allow public access to the list of items
	            .requestMatchers("/api/items/{id}/public").permitAll()  // Allow public access to a specific public item endpoint

	            // Require authentication for specific item-related API
	            .requestMatchers("/api/items/{id}").authenticated()  // Require authentication for accessing individual items

	            // Optionally, apply role-based restrictions
	            //.requestMatchers("/api/items/{id}/secure").hasRole("ADMIN")  // Restrict this path to admin users only

	            // Allow all other requests without authentication
	            .anyRequest().permitAll()  
	        )
	        .httpBasic();  // Enable basic authentication for secured endpoints

	    return http.build();
	}

    @Bean
    public UserDetailsService userDetailsService() {
        // Create some in-memory users for testing purposes
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
