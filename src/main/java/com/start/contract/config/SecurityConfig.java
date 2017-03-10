package com.start.contract.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.start.contract.controller.ContractController;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	private static final String USER="USER";
	private static final String ADMIN="ADMIN";
	
	
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.csrf().disable().authorizeRequests()
		.antMatchers("/svc/v1/private/accounts/*").hasRole(USER)	
		.and()
		.formLogin();
		
	}

	 @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception {
		auth.inMemoryAuthentication()
		.withUser("philp").password("password").roles(USER)
		.and()
		.withUser("starrit").password("password").roles(USER,ADMIN);
		
		
	}
	
   
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
   /*     authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());*/
    }
}
