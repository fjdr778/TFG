package src.rutina.app.Dao;

import java.sql.Date;
import java.util.List;

import src.rutina.app.Objects.Usuario;

/*
 * Clase que representa la interfaz DAO de usuarios. Abstrae las 
 * operaciones necesarias sobre la base de datos relacionadas con los 
 * usuarios a petición del controlador REST de usuarios.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

public abstract interface UsuarioDao {
	
	// Por defecto los usuarios van a crearse con un rol predeterminado 
	// ROLE_USER hasta que tengamos disponible la interfaz de gestión de 
	// administrador
	// También se habilitan todos por defecto, ya que esto es una función que
	// se encontrará disponible de manera funcional próximamente:
	// ENABLED = 1;
	
	public abstract void createUser(String userId, String userName,
			String userPhoneNumber, Date userBirthDate, String userPassw);
	public abstract void updateUser(String userId, String userName,
			String userPhoneNumber, Date userBirthDate, String userPassw);
	public abstract List<Usuario> getUser(String userId);
	public abstract List<Usuario> getAllUsers();
	public abstract void deleteUser(String userId);
	public abstract void deleteAllUsers();
}
