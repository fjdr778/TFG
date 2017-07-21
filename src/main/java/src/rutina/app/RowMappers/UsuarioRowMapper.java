package src.rutina.app.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import src.rutina.app.Objects.Usuario;

/*
 * Clase que representa un mapeador de usuarios. Obtiene y crea un 
 * usuario a partir de los datos obtenidos de la base de datos.
 * Este RowMapper es para obtener los datos de la tabla USERS_INFO
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

public class UsuarioRowMapper implements RowMapper<Usuario> {

    public Usuario mapRow(ResultSet resultSet, int arg1) throws SQLException {
    	
	// Aquí se hace dentro de cada getX(index) con el index el número de
	// la columna empezando por la izquierda de la tabla de usuarios
	// dentro de la base de datos
	//
	// No incluimos la contraseña en el RowMapper
	Usuario user = new Usuario(resultSet.getString(1), resultSet.getString(2),
		resultSet.getString(3), "", resultSet.getDate(4));

	return user;
    }
}
