package com.personal_expenses_management.PEM.config;

import com.personal_expenses_management.PEM.filter.AuthEntryPointJWT;
import com.personal_expenses_management.PEM.filter.JWTFilter;
import com.personal_expenses_management.PEM.service.MyUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private MyUserDetailsService myUserDetailsService;

    private JWTFilter jwtFilter;

    private AuthEntryPointJWT authEntryPointJWT;

    @Autowired
    public void setAuthEntryPointJWT(AuthEntryPointJWT authEntryPointJWT){
        this.authEntryPointJWT = authEntryPointJWT;
    }

    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService){
        this.myUserDetailsService = myUserDetailsService;
    }

    @Autowired
    public void setJwtFilter(JWTFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        return httpSecurity
                .csrf(cors -> {})
                .csrf(customizer -> customizer.disable())
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/register", "/login").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJWT))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user1 = User.withUsername("user1")
//                .password("{noop}pass1")
//                .roles("user")
//                .build();
//
//        UserDetails admin1 = User.withUsername("admin")
//                .password("{noop}pass2")
//                .roles("admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, admin1);
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }
}
