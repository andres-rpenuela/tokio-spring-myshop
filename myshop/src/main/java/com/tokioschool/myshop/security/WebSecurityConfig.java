package com.tokioschool.myshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean("filterSecurityChainMainly")
    public SecurityFilterChain filterChainMainly(HttpSecurity http) throws Exception {
        http.securityMatcher("/**","/myshop/**","/login","/logout")
                .authorizeHttpRequests(req -> req.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.csrf(Customizer.withDefaults());
        http.cors(Customizer.withDefaults());
        return http.build();
    }

    // Login in memory without encrypt password
    // link: https://medium.com/@sahinutkuonur/how-to-implement-spring-security-in-memory-authentication-821d4d02bb93
    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}123")
                .roles("USER") //ROLE_{...}
                .authorities("READ")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}123")
                .roles("USER", "ADMIN") //ROLE_{...}
                .authorities("READ,WRITE")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}

//package com.tokioschool.myshop.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static com.tokioschool.myshop.security.Constants.*;
//
///**
// * Configuración general de seguridad para Spring Security
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
//
//    @Autowired
//    private TokioUserDetailsService userDetailsService;
//
//    /**
//     * Configuración de la autenticación de usuario
//     */
//    //@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    /**
//     * Configuración de seguridad
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/registration").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/new-user").permitAll()
//                .antMatchers("/product/**").permitAll()
//                .antMatchers("/admin/**").hasAuthority(ADMIN_ROLE).anyRequest()
//                .authenticated().and().csrf().disable()
//                .formLogin()
//                    .loginPage(LOGIN_URL)
//                    .defaultSuccessUrl(LOGIN_SUCCESS_URL)
//                    .failureUrl(LOGIN_FAILURE_URL)
//                .and()
//                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
//                    .logoutSuccessUrl(LOGOUT_SUCCESS_URL);
//    }
//
//    /**
//     * Permite el acceso al contenido estático
//     */
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(
//                "/resources/**",
//                "/static/**",
//                "/templates/**",
//                "/css/**",
//                "/js/**",
//                "/images/**",
//                "/fonts/**",
//                "/webjars/**");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//
