package com.ganeshban.smsserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity {

    private final JwtAuthenticationFilter jwtFilter;


    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        return security.securityMatcher("rest/**").authorizeHttpRequests(x -> x.anyRequest().authenticated()).cors(x -> x.configurationSource(corsConfigurationSource())).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).csrf(AbstractHttpConfigurer::disable).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).formLogin(AbstractHttpConfigurer::disable).httpBasic(AbstractHttpConfigurer::disable).build();
    }

    @Bean
    public SecurityFilterChain filterChainAuthorized(HttpSecurity security) throws Exception {
        return security.securityMatcher("/auth/**", "login", "logout", "accessDenied").authorizeHttpRequests(auth -> auth.anyRequest().permitAll()).cors(x -> x.configurationSource(corsConfigurationSource())).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).csrf(AbstractHttpConfigurer::disable).formLogin(AbstractHttpConfigurer::disable).httpBasic(AbstractHttpConfigurer::disable).build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
