package src.rutina.app.Main;

import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;

import src.rutina.app.Constants.SqlConstants;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

/*
 * Clase que implementa toda la seguridad de la aplicación.
 * Incluye aquellos ficheros o URLs de la API que no pueden
 * o pueden visualizarse una vez o no autenticado, además
 * de la protección CSRF.
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


// IMPORTANTE: El tipò de autenticación que usamos es FORM-BASED
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	DataSource dataSource;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
	    builder.jdbcAuthentication().dataSource(dataSource)
	    .usersByUsernameQuery(SqlConstants.GET_USER_AUTHENTICATION)
	    .authoritiesByUsernameQuery(SqlConstants.GET_USER_AUTHORITY);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    	// Las reglas van de más específicas a menos específicas
	    	// antMatchers() indica el patrón sobre el que se aplicará la regla
	    	http.authorizeRequests().antMatchers("/RutinaRegister/**").permitAll();
	    	//http.authorizeRequests().antMatchers("/Rutina_app/**").permitAll();
	    	http.authorizeRequests().antMatchers("/index.html","/login.html","/ownerAdd.html").permitAll();
	    	http.authorizeRequests().antMatchers("/index.html").authenticated();
	    	http.authorizeRequests().antMatchers("/*.html").authenticated();
	    	
	    	http.authorizeRequests().antMatchers("/Rutina_app/**").authenticated();
	    	
	    	http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
	    	
	    	http.formLogin().successHandler(authenticationSuccessHandler);
	    	http.formLogin().failureHandler(authenticationFailureHandler);
	    	http.logout().logoutSuccessUrl("/");
		
	    	http.csrf().disable();
		// CSRF tokens handling
		http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
	}
}
