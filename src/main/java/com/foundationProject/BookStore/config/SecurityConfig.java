package com.foundationProject.BookStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> corsFilter())
                .authorizeHttpRequests(
                        config->config
                                // Public endpoints
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/swagger-ui/index.html/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/books/**", "/api/categories/**", "/api/authors/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/users/{userId}").hasAnyRole("USER", "ADMIN")

                                // User endpoints
                                .requestMatchers(HttpMethod.GET, "/api/orders/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/api/orders/**").hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/api/order-items/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/api/order-items/**").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/api/order-items/**").hasRole("USER")
                                .requestMatchers(HttpMethod.DELETE, "/api/order-items/**").hasRole("USER")
                                .requestMatchers(HttpMethod.PUT, "/api/users/{userId}").hasRole("USER")

                                // Admin and Manager endpoints
                                .requestMatchers(HttpMethod.POST, "/api/books/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMIN")

                                // Default policy
                                .anyRequest().denyAll()
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }




    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
