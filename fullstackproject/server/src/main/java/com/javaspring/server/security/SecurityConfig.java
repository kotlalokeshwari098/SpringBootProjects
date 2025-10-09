package com.javaspring.server.security;

import com.javaspring.server.model.AppRole;
import com.javaspring.server.model.Role;
import com.javaspring.server.model.User;
import com.javaspring.server.repositories.RoleRepository;
import com.javaspring.server.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.Optional;

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
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER);
            if (userRole == null) {
                userRole = roleRepository.save(new Role(AppRole.ROLE_USER));
            }
            Role adminRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN);
            if (adminRole == null) {
                adminRole = roleRepository.save(new Role(AppRole.ROLE_ADMIN));
            }
            if(!userRepository.existsByUsername("user1")){
                User user1=new User("user1","user1@example.com","{noop}authoritiesuser");
                user1.setAccountNonLocked(false);
                user1.setAccountNonExpired(false);
                user1.setCredentialsNonExpired(false);
                user1.setEnabled(false);
                user1.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                user1.setAccountExpiryDate(LocalDate.now().plusYears(1));
                user1.setTwoFactorEnabled(false);
                user1.setTwoFactorEnabled(false);
                user1.setSignUpMethod("email");
                user1.setRole(userRole);
                userRepository.save(user1);
            }

            if(!userRepository.existsByUsername("admin")){
                User admin=new User("admin","admin@example.com","{noop}authoritiesadmin");
                admin.setAccountNonLocked(false);
                admin.setAccountNonExpired(false);
                admin.setCredentialsNonExpired(false);
                admin.setEnabled(false);
                admin.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
                admin.setTwoFactorEnabled(false);
                admin.setTwoFactorEnabled(false);
                admin.setSignUpMethod("email");
                admin.setRole(userRole);
                userRepository.save(admin);
            }
        };
    }
}
