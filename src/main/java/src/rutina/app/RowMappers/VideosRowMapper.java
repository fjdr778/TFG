package src.rutina.app.RowMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import src.rutina.app.Objects.*;


/*
 * Clase que representa un mapeador de Videos. Obtiene y crea un Video
 * a partir de los datos obtenidos de la base de datos.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

public class VideosRowMapper implements RowMapper<Videos> {
    
    public Videos mapRow(ResultSet resultSet, int arg1) throws SQLException {
    	
	// Aquí se hace dentro de cada getX(index) con  el index el número de 
	// la columna empezando por la izquierda de la tabla de eventos 
	// dentro de la base de datos
    	
	Videos videos = new Videos(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4));

	return videos;
    }
}

