package src.rutina.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/*
 * Clase que representa un mapeador de eventos. Obtiene y crea un evento
 * a partir de los datos obtenidos de la base de datos.
 * 
 * 
* Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */


public class EjercicioRowMapper implements RowMapper<Ejercicio> {
    
    public Ejercicio mapRow(ResultSet resultSet, int arg1) throws SQLException {
	// Aquí se hace dentro de cada getX(index) con  el index el número de 
	// la columna empezando por la izquierda de la tabla de ejercicios
	// dentro de la base de datos
	Ejercicio ejercicio = new Ejercicio(resultSet.getInt(1), resultSet.getString(2),
		resultSet.getString(3), resultSet.getString(4),
		resultSet.getString(5), resultSet.getString(6),
		resultSet.getInt(7), resultSet.getInt(8),"");

	return ejercicio;
    }
}
