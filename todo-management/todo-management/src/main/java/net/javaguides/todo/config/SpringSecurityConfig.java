package net.javaguides.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {


/*
* This function will allow all the basic http request to flow properly
*  authorize.anyRequest().authenticated()  --> using this line we authenticated all the incoming http requests.
*
* We have configured Spring Security in such a way that http basic authentication
* */
        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf().disable().authorizeHttpRequests((authorize)->{
                authorize.anyRequest().authenticated();
            }).httpBasic(Customizer.withDefaults());

            return  httpSecurity.build();

        }
}
