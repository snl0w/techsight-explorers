package br.com.tcse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
       return httpSecurity
               .authorizeHttpRequests( auth -> auth
                       .requestMatchers("/").permitAll()
                       .requestMatchers("/css/*").permitAll()
                       .requestMatchers("/images/*").permitAll()
                       .requestMatchers("/js/*").permitAll()
                       .requestMatchers("/menu").permitAll()
                       .requestMatchers("/login").permitAll()
                       .requestMatchers("/register").permitAll()
                       .requestMatchers("/forgotPassword").permitAll()
                       .requestMatchers("/members").permitAll()
                       .anyRequest().authenticated()
               )
               .formLogin(form -> form
                       .loginPage("/login") // Indica sua página personalizada de login
                       .defaultSuccessUrl("/", true) // Página para redirecionar após login bem-sucedido
                       .permitAll() // Permite acesso à página de login sem autenticação
               )
               .logout(config -> config.logoutSuccessUrl("/"))
               .build();
   }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
