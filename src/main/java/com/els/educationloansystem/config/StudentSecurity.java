package com.els.educationloansystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import com.els.educationloansystem.jwt.JWTAuthenticationEntryPoint;
import com.els.educationloansystem.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class StudentSecurity {
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;


	private final CorsConfigurationSource corsConfigurationSource;

	public StudentSecurity(CorsConfigurationSource corsConfigurationSource) {
		this.corsConfigurationSource = corsConfigurationSource;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	    http
//	        .csrf(csrf -> csrf.disable())
//	        .authorizeHttpRequests(auth -> auth
//	            .anyRequest().permitAll()
//	        );
//	    return http.build();
//	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource))
			.csrf(csrf -> csrf.disable())

			.authorizeHttpRequests(auth -> auth

				// 🔓 PUBLIC APIs
				.requestMatchers(
						"/api/auth/login",
				        "/api/auth/register",
				        "/api/admin/login"
				).permitAll()

				// 🔐 ADMIN APIs
				.requestMatchers("/api/admin/**").hasRole("ADMIN")

				// 🔐 STUDENT APIs
				.requestMatchers("/api/student/**").hasRole("STUDENT")

				// 🔒 Everything else
				.anyRequest().authenticated()
			)

			.exceptionHandling(ex ->
				ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			)

			.sessionManagement(session ->
				session.sessionCreationPolicy(
					org.springframework.security.config.http.SessionCreationPolicy.STATELESS
				)
			);

		// 🔥 JWT FILTER
		http.addFilterBefore(
			jwtAuthenticationFilter,
			org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class
		);

		return http.build();
	}
}