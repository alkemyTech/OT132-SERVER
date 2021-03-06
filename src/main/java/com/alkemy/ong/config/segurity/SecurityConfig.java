package com.alkemy.ong.config.segurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] SWAGGER_ENDPOINTS = {
      "/api/docs",
      "/v2/api-docs",
      "/swagger-ui/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**"
  };

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new CustomAuthenticationEntryPoint();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
    managerBuilder.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .cors()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/auth/login")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/auth/register")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/auth/me")
        .hasAnyRole(RoleType.USER.name())
        .antMatchers(SWAGGER_ENDPOINTS)
        .permitAll()
        .antMatchers(HttpMethod.GET, "/organization/public")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/organization/public")
        .hasAnyRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/categories")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.POST, "/categories")
        .hasAnyRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.PUT, "/categories/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/activities")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.PUT, "/activities/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.POST, "/contacts")
        .hasAnyRole(RoleType.USER.name())
        .antMatchers(HttpMethod.GET, "/contacts")
        .hasAnyRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/users")
        .hasRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/users/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.PATCH, "/users/{id:[\\d+]}")
        .hasAnyRole(RoleType.USER.name(), RoleType.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/slides")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.POST, "/slides")
        .hasAnyRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.PUT, "/slides/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/testimonials")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.POST, "/testimonials")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.DELETE, "/testimonials/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.PUT, "/testimonials/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.GET, "/members")
        .hasRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/members")
        .hasRole(RoleType.USER.name())
        .antMatchers(HttpMethod.PUT, "/members/{id:[\\d+]}")
        .hasRole(RoleType.USER.name())
        .antMatchers(HttpMethod.POST, "/news")
        .hasRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/news/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/news")
        .hasAnyRole(RoleType.USER.name())
        .antMatchers(HttpMethod.GET, "/news/{id:[\\d+]}/comments")
        .hasAnyRole(RoleType.USER.name())
        .antMatchers(HttpMethod.GET, "/comments")
        .hasAnyRole(RoleType.USER.name(), RoleType.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/comments")
        .hasAnyRole(RoleType.USER.name())
        .antMatchers(HttpMethod.PUT, "/comments/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.DELETE, "/comments/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name(), RoleType.USER.name())
        .antMatchers(HttpMethod.GET, "/categories/{\\d+}")
        .hasRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/categories/{id:[\\d+]}")
        .hasAnyRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/slides/{id:[\\d+]}")
        .hasRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/slides/{\\d+}")
        .hasAnyRole(RoleType.USER.name(), RoleType.ADMIN.name())
        .antMatchers(HttpMethod.PUT, "/news/{id:[\\d+]}")
        .hasRole(RoleType.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/news/{id:[\\d+]}")
        .hasAnyRole(RoleType.USER.name(), RoleType.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/members/{id:[\\d+]}")
        .hasRole(RoleType.ADMIN.name())
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler())
        .authenticationEntryPoint(authenticationEntryPoint());
  }

}
