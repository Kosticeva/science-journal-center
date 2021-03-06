package com.uns.ftn.sciencejournal.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// TODO: 8/24/2019 activate
@Configuration
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/**").permitAll();
                /*.antMatchers("/h2-console**", "/rest/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/papers/download/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/credentials", "/api/users").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated();*/

        httpSecurity
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

}
