package src.rutina.app.Main;


import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import src.rutina.app.Constants.UriConstants;
import src.rutina.app.DaoImpl.UsuarioDaoImpl;
import src.rutina.app.Objects.Usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;

/*
 * Clase que representa un controlador REST de usuarios. Mapea las 
 * operaciones sobre recursos REST relacionados con usuarios y hace uso 
 * del DAO para hacerlas efectivas en la base de datos.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

@RestController
@ImportResource("classpath:spring/config/beanLocations.xml")
public class UsuarioController {
	
	// Obtenemos el DAO mediante inyección de dependencias
	@Autowired
	private UsuarioDaoImpl userDao;

	// Añade un Usuario
	@RequestMapping(value = UriConstants.USUARIOS_REGISTER, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addUser(@RequestBody Usuario user) {

		userDao.createUser(user.getUserId(), user.getUserName(),
				user.getUserPhoneNumber(), user.getUserBirthDate(),
				user.getUserPassw());
	}

	//Modifica un Usuario
	@RequestMapping(value = UriConstants.USUARIO, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@PathVariable("user_id") String UserId,
			@RequestBody Usuario User) {

		userDao.updateUser(UserId, User.getUserName(),
				User.getUserPhoneNumber(), User.getUserBirthDate(),
				User.getUserPassw());
	}

	// Obtiene un Usuario
	@RequestMapping(value = UriConstants.USUARIO, method = RequestMethod.GET)
	public @ResponseBody List<Usuario> getUser(
			@PathVariable("user_id") String userId) {

		return this.userDao.getUser(userId);
	}

	// Obtiene todos los Usuarios
	@RequestMapping(value = UriConstants.ALL_USUARIOS, method = RequestMethod.GET)
	public @ResponseBody List<Usuario> getAllUsers() {

		return this.userDao.getAllUsers();
	}

	// Elimina un Usuario
	@RequestMapping(value = UriConstants.USUARIO, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("user_id") String userId) {

		userDao.deleteUser(userId);
	}

	// Elimina todos los Usuarios
	@RequestMapping(value = UriConstants.ALL_USUARIOS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllUsers() {

		userDao.deleteAllUsers();
	}


}
