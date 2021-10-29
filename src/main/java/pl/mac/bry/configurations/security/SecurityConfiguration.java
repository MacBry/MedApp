package pl.mac.bry.configurations.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api").permitAll()
		.antMatchers("/api/users").permitAll()
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
