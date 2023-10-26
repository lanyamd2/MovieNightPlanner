package com.sparta.projectmovie1.movienightplanner.loginconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private final JpaUserDetailsService jpaUserDetailsService;

    public WebSecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests)->
                        requests.requestMatchers("/",
                                        "/home",
                                        "/index",
                                        "/registration",
                                        "/search-results-new/**",
                                        "/details/**",
                                        "/api/**",
                                        "/css/**",
                                        "/images/**",
                                        "/fonts/**",
                                        "/scripts/**"
                                )
                                .permitAll().anyRequest().authenticated()
                )
                .userDetailsService(jpaUserDetailsService)
                .formLogin((form)-> form.loginPage("/login")
                        .permitAll()
                ).logout((logout)->logout.permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }
}
