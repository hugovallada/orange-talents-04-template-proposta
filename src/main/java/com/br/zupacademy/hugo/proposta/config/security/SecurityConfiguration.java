package com.br.zupacademy.hugo.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

//@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequest ->
                authorizeRequest
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_proposta:read")
                        .antMatchers(HttpMethod.GET,"/cartoes/**").hasAuthority("SCOPE-cartoes:read")
                        .antMatchers(HttpMethod.POST,"/cartoes/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_proposta:write")
                        .antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_infra:read")
                        .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
