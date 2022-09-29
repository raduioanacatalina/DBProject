//package com.db.connectville.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter{
//
//    @Lazy
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests().antMatchers("/*").permitAll().and().formLogin();
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                    .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests()
//                .antMatchers("/users/login")
//    }
//}
