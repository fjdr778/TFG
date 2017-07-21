package src.rutina.app.Main;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Clase que representa el manejador (handler) que se ejecuta 
 * en el caso de que un usuario se autentique de manera incorrecta.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * version 2.0.0
 *
 */

@Component
public class RESTAuthenticationFailureHandler extends 
				SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, 
		HttpServletResponse response,
		AuthenticationException exception) throws IOException, 
	ServletException {
		super.onAuthenticationFailure(request, response, exception);
	}
}