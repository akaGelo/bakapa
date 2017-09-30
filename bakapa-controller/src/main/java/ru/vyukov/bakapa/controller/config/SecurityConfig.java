package ru.vyukov.bakapa.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import ru.vyukov.bakapa.controller.domain.Agent;
import ru.vyukov.bakapa.controller.service.agents.AgentsService;

@Configuration
// @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String AGENT = Agent.getDefaultAgentRole();
	@Autowired
	private AgentsService agentsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(agentsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.antMatchers("/private/**","/").permitAll() //
				.antMatchers("/public/**").authenticated()
				.anyRequest().authenticated() //
				.and()//
				.httpBasic();
	}

}