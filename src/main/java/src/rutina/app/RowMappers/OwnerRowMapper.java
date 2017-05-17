package src.rutina.app.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import src.rutina.app.Objects.Owner;

/*
 * Clase que representa un mapeador de propietarios. Obtiene y crea un 
 * propietario a partir de los datos obtenidos de la base de datos.
 * Este RowMapper es para obtener los datos de la tabla OWNERS_INFO
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */

public class OwnerRowMapper implements RowMapper<Owner> {

    public Owner mapRow(ResultSet resultSet, int arg1) throws SQLException {
	// Aquí se hace dentro de cada getX(index) con el index el número de
	// la columna empezando por la izquierda de la tabla de eventos
	// dentro de la base de datos
	//
	// No incluimos la contraseña en el RowMapper
	Owner owner = new Owner(resultSet.getString(1), resultSet.getString(2),
		resultSet.getString(3), "", resultSet.getDate(4));

	return owner;
    }
}
