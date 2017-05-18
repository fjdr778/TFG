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
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */

public class VideosDaoImpl implements VideosDao {
    
    JdbcTemplate jdbcTemplate;

    @Override
    public  void createVideo(String videoNombre, String videoUrl,int ej_id,String ownerId){
    	jdbcTemplate.update(SqlConstants.CREATE_VIDEO,
    	    	new Object[] {videoNombre,videoUrl,ej_id,ownerId});
    	
    }
    @Override
    public List<Videos> getVideo(int ej_id,String ownerId){
    	return jdbcTemplate.query(SqlConstants.GET_VIDEO,
				new Object[] { ej_id,ownerId},
				new VideosRowMapper());
	 }
    

    @Override
    public void deleteVideo(int ej_id,String ownerId){
    	jdbcTemplate.update(SqlConstants.DELETE_VIDEO,
    			new Object[] { ej_id,ownerId });
	 }

    @Override
    public void updateVideo(String videoNombre, String videoUrl,int ej_id,String ownerId){
    	jdbcTemplate.update(SqlConstants.UPDATE_VIDEO,
    	    	new Object[] {videoNombre,videoUrl,ej_id,ownerId});
	 }
    
    
    // Inyección del dataSource mediante el constructor
    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public VideosDaoImpl() {
    }
}  
	
	 
	 
	 
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*@Override
	public void createRutina(String rutinaNombre, String rutinaDescripcion, String rutinaInfo_Rutina,boolean rutinaPub_Priv,String ownerId) {
    	
    	jdbcTemplate.update(SqlConstants.CREATE_RUTINA,
    	new Object[] { rutinaNombre, rutinaDescripcion, rutinaInfo_Rutina, ownerId });
		
	}
    
    @Override
    public void updateRutina(int rut_id, String rutinaNombre, String rutinaDescripcion,
    	    String rutinaInfo_Rutina,boolean rutinaPub_Priv,String ownerId) {
    	jdbcTemplate.update(SqlConstants.UPDATE_RUTINA,
    		new Object[] { rut_id,rutinaNombre, rutinaDescripcion,rutinaInfo_Rutina,
    				ownerId});
        }



    @Override
	public List<Rutina> getRutina(String ownerId, int rut_id) {
		return jdbcTemplate.query(SqlConstants.GET_RUTINA,
				new Object[] { ownerId,rut_id },
				new RutinaRowMapper());
	}
    
    
    @Override
	public List<Rutina> getAllRutinas(String ownerId) {	
		return jdbcTemplate.query(SqlConstants.GET_ALL_RUTINAS,
				new Object[] {ownerId},
				new RutinaRowMapper()) ;
	}
    @
    Override
    public void deleteRutina(String ownerId,int rut_id) {
	jdbcTemplate.update(SqlConstants.DELETE_RUTINA,
		new Object[] { ownerId,rut_id });
    }
    @Override
    public void deleteAllRutinas(String ownerId) {
	jdbcTemplate.update(SqlConstants.DELETE_ALL_RUTINAS,
		new Object[] {ownerId});
    }
    
  
    // Inyección del dataSource mediante el constructor
    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public RutinaDaoImpl() {
    }*/

