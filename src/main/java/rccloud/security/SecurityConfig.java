package rccloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import rccloud.AppUser;
import rccloud.data.UserRepository;

@Configuration
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
		return username -> {
			AppUser user = userRepository.findByUsername(username);
			if (user != null) return user;
			
			throw new UsernameNotFoundException("User '" + username + "' not found!");
		};
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf()
					.disable() // TODO: remove these 2 lines to enable CSRF protection in Production
				.authorizeRequests()
					.antMatchers("/create", "/orders/*").hasRole("USER")
					.antMatchers("/h2-console/**").permitAll()
					.antMatchers("/", "/**").access("permitAll()")
				.and()
					.headers().frameOptions().sameOrigin() // fix h2-console forbidden error
				.and()
					.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/create")
				.and()
					.oauth2Login()
						.loginPage("/login")
						.defaultSuccessUrl("/create")
				.and()
					.logout()
						.logoutSuccessUrl("/")
				.and()
					.build();
	}
	
}
