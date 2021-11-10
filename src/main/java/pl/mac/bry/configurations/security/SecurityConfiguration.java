package pl.mac.bry.configurations.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/loginform").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/registerform").permitAll()
		.antMatchers("/resetPasswordForm").permitAll()
		.antMatchers("/resetPassword").permitAll()
		.antMatchers("/logout").permitAll()
		.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/loginform")
				.and()
				.logout()
				.logoutSuccessUrl("/loginform");
				
				
				
		        
	}

}