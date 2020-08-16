package com.hmm.hmm.config;

import com.hmm.hmm.filter.JwtAuthenticationFilter;
import com.hmm.hmm.utils.JwtUtil;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

  @Value("${jwt.secret}")
  private String secret;



  @Override
  protected void configure(HttpSecurity http) throws Exception {

    Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil());

    http.addFilter(filter)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .cors().disable()
        .csrf().disable()
        .formLogin().disable();
  }

  @Bean
  public JwtUtil jwtUtil() {
    return new JwtUtil(secret);
  }
}
