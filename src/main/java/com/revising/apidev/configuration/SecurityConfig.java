package com.revising.apidev.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable); // FROM 6.1 onwards csrf().disable is deprecated.

//        httpSecurity.authorizeHttpRequests(request-> request.requestMatchers(HttpMethod.POST).authenticated());
        httpSecurity.authorizeHttpRequests(request->
                request.requestMatchers("login","register").permitAll()
//                        .requestMatchers("abc").hasRole("ADMIN")
                .anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults());
         httpSecurity.httpBasic(Customizer.withDefaults());
         httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
         httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
         return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
////        UserDetails userDetails = User.withUsername("xyz")
////                .password("{noop}abc").roles("ADMIN","FACTORY_USER").build(); // here not using any password encode
//
//        UserDetails userDetails = User.withUsername("xyz").password("abcde")
//                .passwordEncoder(password->{
//                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//                    return passwordEncoder.encode(password);
//                }).build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
