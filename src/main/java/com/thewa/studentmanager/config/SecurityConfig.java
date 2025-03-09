package com.thewa.studentmanager.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	  http.csrf(AbstractHttpConfigurer::disable)
		  // just for testing data using H2
		  .headers(headers -> headers
				  .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
		  .authorizeHttpRequests(auth -> auth
				  .requestMatchers("/h2-console/**").permitAll()
				  .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
				  .requestMatchers("/api/admin/**").hasRole("ADMIN")
				  .anyRequest().permitAll())
		  .httpBasic(Customizer.withDefaults());
	  return http.build();
   }
   
   @Bean
   public UserDetailsService userDetailsService() {
	  UserDetails admin = User.builder()
							  .username("admin")
							  .password(passwordEncoder().encode("admin123"))
							  .roles("ADMIN")
							  .build();
	  return new InMemoryUserDetailsManager(admin);
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
   }
}