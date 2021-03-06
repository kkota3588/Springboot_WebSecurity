package com.krishna.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	private static LoggerUtility logger;

	// Authentication : User --> Roles
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		logger.getLogger().info("Autherizations.....");
		auth.inMemoryAuthentication()
				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
				.withUser("krishna").password("krish@123").roles("USER").and().withUser("admin1").password("krish@123")
				.roles("USER", "ADMIN");
	}

	// Authorization : Role -> Access
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		logger.getLogger().info("Autherization role access disabling...");
		http.httpBasic().and().authorizeRequests().antMatchers("/showAll/**", "/insert/**", "/retrive/**", "/delete/**")
				.hasRole("USER").antMatchers("/**").hasRole("ADMIN").and().csrf().disable().headers().frameOptions()
				.disable();

	}

}


 
