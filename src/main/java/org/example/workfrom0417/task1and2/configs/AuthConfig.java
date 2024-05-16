package org.example.workfrom0417.task1and2.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class AuthConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/schools/*/students/*").permitAll() // Everyone can access /schools/*/students/* EP
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails student = User
                .withUsername("student")
                .password(passwordEncoder().encode("student1"))
                .roles("STUDENT")
                .build();

        UserDetails teacher = User
                .withUsername("teacher")
                .password(passwordEncoder().encode("teacher1"))
                .roles("TEACHER")
                .build();

        UserDetails guest = User
                .withUsername("guest")
                .password(passwordEncoder().encode("guest1"))
                .roles("GUEST")
                .build();

        UserDetails guest2 = User
                .withUsername("guest2")
                .password(passwordEncoder().encode("guest2"))
                .roles("GUEST")
                .build();

        return new InMemoryUserDetailsManager(student, teacher, guest, guest2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}

