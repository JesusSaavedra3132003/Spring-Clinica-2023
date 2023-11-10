package com.project.consorcio.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//habilitar configuraciòn
@Configuration
//habilitar seguridad
@EnableWebSecurity
public class WebSecurityConfig {
	
	//metodo para habilitar encriptar clave
	@Bean
	public BCryptPasswordEncoder encriptarClave() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/session/**","resources/**").permitAll() //hacer ajustes aqui, para que permita registrar y actualizar
				.requestMatchers("/medicamento/**","/paciente/**","/requerimiento/**").authenticated()
				
			)
			.formLogin((form) -> form
				.loginPage("/session/login")
				.defaultSuccessUrl("/session/intranet")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		return new SecurityLogin();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		//crear objeto
		DaoAuthenticationProvider configure=new DaoAuthenticationProvider();
		//setear
		configure.setUserDetailsService(userDetailsService());
		configure.setPasswordEncoder(encriptarClave());
		return configure;
	}

}
