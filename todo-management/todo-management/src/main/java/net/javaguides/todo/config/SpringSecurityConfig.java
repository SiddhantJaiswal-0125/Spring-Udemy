package net.javaguides.todo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
@AllArgsConstructor

public class SpringSecurityConfig {


/*
* This function will allow all the basic http request to flow properly
*  authorize.anyRequest().authenticated()  --> using this line we authenticated all the incoming http requests.
* We have configured Spring Security in such a way that http basic authentication
* */


    private UserDetailsService userDetailsService;
    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return  new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests((authorize) -> {
//                authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//            authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//
//            authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN", "USER");
//            authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();

            authorize.anyRequest().authenticated();


        }).httpBasic(Customizer.withDefaults());
        return  httpSecurity.build();
        }


        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
            return  configuration.getAuthenticationManager();
        }

//        @Bean
//        public UserDetailsService userDetailsService()
//        {
//            UserDetails ramesh = User.builder().username("ramesh").password(passwordEncoder().encode("password")).roles("USER").build();
//            UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode( "admin")).roles("ADMIN").build();
//            return  new InMemoryUserDetailsManager(ramesh, admin);
//        }

}
