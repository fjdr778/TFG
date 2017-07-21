package src.rutina.app.Main;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Clase que representa el punto de entrada que se ejecuta 
 * en el caso de que un usuario no autenticado intente acceder 
 * a nuestro servicio.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * version 2.0.0
 *
 */

@Component
public class RESTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, 
		HttpServletResponse response, 
		AuthenticationException authException)
			throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
			"No tiene autorización para acceder a este contenido");
	}
}
