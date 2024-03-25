package com.example.marathon.config;

import com.example.marathon.authorization.TestEndpointAuthorizationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        //authentication
                http.httpBasic().realmName("real_name");
        //the same as
               // http.httpBasic(c->c.realmName("real_name"));

        //authorization
               http.authorizeHttpRequests()
                       .anyRequest().authenticated();


        //authorization
        //
        return http.build();

    }
    @Bean
    public UserDetailsService detailsService(){
        UserDetails user = User.builder()
                .username("bill")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(user);


    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

         */
        http.httpBasic();
        http.authorizeHttpRequests()
                .requestMatchers("/save").permitAll()
                .requestMatchers("/demo").hasAuthority("read")
                .requestMatchers("/hello").hasAuthority("read")
                .requestMatchers("/smth").access(
                        new WebExpressionAuthorizationManager("isAuthenticated()"))
                .anyRequest().authenticated();


        http.csrf().ignoringRequestMatchers("/save");

        return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
