package com.dorjear.sample.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * XXX: In the real world, we need to lookup user information from some
		 * database or LDAP. Hard-coding dummy user for demo purpose.
		 */
		auth.inMemoryAuthentication().withUser("optus").password("candidates").roles("USER");

	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		((HttpSecurity) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http.authorizeRequests()
				.anyRequest()).authenticated().and()).formLogin().and()).httpBasic();
		http.csrf().disable();
	}

}
