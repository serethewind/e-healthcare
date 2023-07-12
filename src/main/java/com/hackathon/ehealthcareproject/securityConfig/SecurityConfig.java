package com.hackathon.ehealthcareproject.securityConfig;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;
    private JwtAuthEntryPoint authEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;
    private LogoutService logoutService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(authEntryPoint))

                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> {
                    authorize
                            .requestMatchers(HttpMethod.POST, "/api/health/v1/auth/**").permitAll()
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers("/index.html").permitAll()
                            .requestMatchers("api/v1/health/doctor/**").permitAll()
                            .requestMatchers( "/api/health/v1/users/**").permitAll()
                            .requestMatchers("/api/health/v1/products/**").permitAll()
                            .requestMatchers("/api/health/v1/carts/**").permitAll()
                            .requestMatchers("/api/health/v1/appointment/**").permitAll()
//                            .requestMatchers(HttpMethod.POST, "/api/health/v1/appointment/").hasAuthority("USER")
                   .anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.logout((logout) ->
                logout
                        .logoutUrl("/api/health/v1/auth/logout")
                        .addLogoutHandler(logoutService)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        );
        return http.build();
    }

}

