package com.alkemy.ong.config.segurity;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SegurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                /*.antMatchers("/auth/**",
                        "/register",
                        "/login",
                        "/v3/api-docs/swagger-config",
                        "/api/swagger-ui/**",
                        "/v3/api-docs",
                        "/api/docs",
                        "/api/docs/**",
                        "/contacts")
                .permitAll()*/
                .antMatchers(HttpMethod.GET,
                        "/categories/{id}",
                        "/categories",
                        "/s3/images",
                        "/organization/public",
                        "/organization/public/{id}",
                        "/contacts",
                        "/users",
                        "/testimonials").hasAnyAuthority("ADMIN","USER")
                .antMatchers(HttpMethod.GET,
                        "/news**").hasAuthority("USER")
                .antMatchers(HttpMethod.GET,
                        "/members",
                        "/comments").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/testimonials",
                        "/members",
                        "/categories",
                        "/s3/images",
                        "/organization/public",
                        "/contacts").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/testimonials",
                        "/members/{id}",
                        "/categories/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "comments/{id}").hasAnyAuthority("ADMIN","USER")
                .antMatchers(HttpMethod.PATCH,
                        "/organization/public/{id}",
                        "/users/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/testimonials/{id}",
                        "/members/{id}",
                        "/categories/{id}",
                        "/users/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/comments/{id}").hasAnyAuthority("ADMIN","USER")
                .anyRequest().authenticated().and().sessionManagement();

        /*http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);*/

    }

}
