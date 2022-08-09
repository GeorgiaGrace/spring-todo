package dev.cameronsims.springtodo.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import dev.cameronsims.springtodo.security.auth.user.AppUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(
    prePostEnabled = true
)
public class SecurityConfig {

    @Autowired
    private AppUserDetailsService appUserService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);

        return provider;

    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {

        http
            .userDetailsService(appUserService)
            .csrf().disable() // TODO: Enable CSRF
            .authenticationProvider(daoAuthenticationProvider())
            .authorizeHttpRequests((req) -> req
                .antMatchers("/").permitAll()
                .antMatchers("/**/favicon.ico").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/common/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .permitAll()
                .loginProcessingUrl("/login").permitAll()
                .defaultSuccessUrl("/app")
            )
            .logout( logout -> logout 
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
            )
            .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
                .key("US44@P14n0");

        return http.build();

    }
    
}
