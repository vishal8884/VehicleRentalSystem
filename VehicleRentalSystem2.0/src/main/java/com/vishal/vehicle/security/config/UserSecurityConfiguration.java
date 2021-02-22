package com.vishal.vehicle.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@Order(3)
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	UserDetailsService getUserDetailService;
	
	@Bean
	public PasswordEncoder passwordEncoder2()//this meathod says i am okay with clear text password
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();	
//	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder2());
		
		return daoAuthenticationProvider;	
	}

	
//	configure method
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		//set your config on auth object
//		auth.inMemoryAuthentication()
//		.withUser("roots")
//		.password("roots")
//		.roles("ADMIN");
//		
//	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/reguser","/","showreg").permitAll();
		
		
		http
		.antMatcher("/user/*")
		.authorizeRequests().anyRequest().hasAnyAuthority("USER")  //before it was .aunthicated   NOW ITS .hasAnyRole("ADMIN")
		.and().formLogin().loginPage("/user/login")
			.defaultSuccessUrl("/user/dashboard", true)
		.permitAll()
		.and().logout().logoutUrl("/user/logout").logoutSuccessUrl("/user/login")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
	http.csrf().disable();
	}
}
