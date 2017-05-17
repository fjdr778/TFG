package src.rutina.app.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import src.rutina.app.Objects.EjerciciosDeRutina;


/*
 * Clase que representa un mapeador de eventos. Obtiene y crea un evento
 * a partir de los datos obtenidos de la base de datos.
 * 
 * 
* Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */


public class EjerciciosDeRutinaRowMapper implements RowMapper<EjerciciosDeRutina> {
    
    public EjerciciosDeRutina mapRow(ResultSet resultSet, int arg) throws SQLException {
	// Aquí se hace dentro de cada getX(index) con  el index el número de 
	// la columna empezando por la izquierda de la tabla de ejercicios
	// dentro de la base de datos
	EjerciciosDeRutina ejercicioderutina = new EjerciciosDeRutina(resultSet.getInt(1),resultSet.getInt(2));

	System.out.println("RowMapper");
	System.out.println(ejercicioderutina.getEj_id());
	System.out.println(ejercicioderutina.getRut_id());
	return ejercicioderutina;
    }
}

