package src.rutina.app.DaoImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import src.rutina.app.Objects.*;
import src.rutina.app.Constants.*;
import src.rutina.app.RowMappers.VideosRowMapper;
import src.rutina.app.Dao.*;

/*
 * Clase que implementa la interfaz DAO de Videos. Realiza las operaciones
 * necesarias sobre la base de datos relacionadas con los videos a petición
 * del controlador REST de videos. No se comentan los métodos debido a que
 * se corresponden con las operaciones del controlador, con lo que ya están
 * descritos previamente.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

public class VideosDaoImpl implements VideosDao {
    
    JdbcTemplate jdbcTemplate;

    @Override
    public  void createVideo(String videoNombre, String videoUrl,int ej_id,String userId){
    	
    	jdbcTemplate.update(SqlConstants.CREATE_VIDEO,
    	    	new Object[] {videoNombre,videoUrl,ej_id,userId});
    	
    }
    @Override
    public List<Videos> getVideo(int ej_id,String userId){
    	//System.out.println(ej_id+userId);
    	return jdbcTemplate.query(SqlConstants.GET_VIDEO,
				new Object[] { ej_id,userId},
				new VideosRowMapper());
	 }
    

    @Override
    public void deleteVideo(int ej_id,String userId){
    	
    	jdbcTemplate.update(SqlConstants.DELETE_VIDEO,
    			new Object[] { ej_id,userId });
	 }

    @Override
    public void updateVideo(String videoNombre, String videoUrl,int ej_id,String userId){
    	
    	jdbcTemplate.update(SqlConstants.UPDATE_VIDEO,
    	    	new Object[] {videoNombre,videoUrl,ej_id,userId});
	 }
    
    
    // Inyección del dataSource mediante el constructor
    public void setDataSource(DataSource dataSource) {
    	
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public VideosDaoImpl() {
    }
}  


