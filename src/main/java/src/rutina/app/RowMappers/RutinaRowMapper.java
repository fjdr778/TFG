package src.rutina.app.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import src.rutina.app.Objects.Rutina;

/*
 * Clase que representa un mapeador de Rutinas. Obtiene y crea una Rutina
 * a partir de los datos obtenidos de la base de datos.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

public class RutinaRowMapper implements RowMapper<Rutina> {

	public Rutina mapRow(ResultSet resultSet, int arg1) throws SQLException {
		
		// Aquí se hace dentro de cada getX(index) con  el index el número de 
		// la columna empezando por la izquierda de la tabla de rutinas
		// dentro de la base de datos
		
		Rutina rutina = new Rutina(resultSet.getInt(1), resultSet.getString(2),
				resultSet.getString(3),resultSet.getString(4),resultSet.getBoolean(5),"");

		return rutina;
	}
}
