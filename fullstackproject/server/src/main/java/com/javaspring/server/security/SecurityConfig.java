package com.javaspring.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests
//                        .requestMatchers("/contact").permitAll()
//                        .requestMatchers("/public/**").permitAll()
//                        .requestMatchers("/admin").denyAll()
//                        .requestMatchers("/admin/**").denyAll()
                        .anyRequest().authenticated());
        //http.formLogin(withDefaults());
        http.csrf(csrf->csrf.disable());
        http.sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(withDefaults());
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager =
                new JdbcUserDetailsManager(dataSource);
        if(!manager.userExists("user1")){
            manager.createUser(
                    User.withUsername("user1")
                            .password("{noop}shashi09")
                            .roles("admin")
                            .build()
            );
        }
        if(!manager.userExists("likii")){
            manager.createUser(
                    User.withUsername("likii")
                            .password("{noop}loki09")
                            .roles("admin")
                            .build()
            );
        }
        return manager;
    }
}
