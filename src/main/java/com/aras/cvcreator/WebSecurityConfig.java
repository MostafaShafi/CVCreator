package com.aras.cvcreator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/font/**", "/templates/**", "/**", "/register", "/signin").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
                .build();
    }
}
