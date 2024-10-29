package com.pharmanet.security.config;

import com.pharmanet.security.filter.JwtTokenValidator;
import com.pharmanet.security.utils.JwtUtils;
import com.pharmanet.service.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // EndPoints publicos
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    // EndPoints privados
                    http.requestMatchers(HttpMethod.GET, "/api/v1/presentations/all").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/presentations/add").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/presentations/delete/{id}").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/presentations/update/{id}").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/laboratories/all").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/laboratories/add").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/laboratories/delete/{id}").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/laboratories/update/{id}").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/providers/all").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/providers/add").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/providers/delete/{id}").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/providers/update/{id}").hasRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/products/all").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.GET, "/api/v1/products/catalogs").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/products/add").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/products/{productId}/lotes").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/products/delete/{id}").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/products/update/{id}").hasRole("ADMIN");



                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
