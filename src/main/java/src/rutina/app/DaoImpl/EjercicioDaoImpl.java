package src.rutina.app.DaoImpl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import src.rutina.app.Constants.SqlConstants;
import src.rutina.app.Dao.EjercicioDao;
import src.rutina.app.Objects.Ejercicio;
import src.rutina.app.RowMappers.EjercicioRowMapper;

/*
 * Clase que implementa la interfaz DAO de eventos. Realiza las operaciones
 * necesarias sobre la base de datos relacionadas con los eventos a petición
 * del controlador REST de eventos. No se comentan los métodos debido a que
 * se corresponden con las operaciones del controlador, con lo que ya están
 * descritos previamente
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */

public class EjercicioDaoImpl implements EjercicioDao {
    
    JdbcTemplate jdbcTemplate;

    public void createEjercicio(String ejercicioNombre, String ejercicioTitulo,
    	    String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
    	    int ejercicioRepeticiones, boolean ejercicio_Pub_Priv,String ownerId) {
	jdbcTemplate.update(SqlConstants.CREATE_EJERCICIO,
		new Object[] { ejercicioNombre, ejercicioTitulo,
	    	    ejercicioSubtitulo,ejercicioDescripcion,ejercicioEstado_Forma, 
	    	    ejercicioRepeticiones,ejercicio_Pub_Priv,ownerId });
    }

    public void updateEjercicio(int ej_id, String ejercicioNombre, String ejercicioTitulo,
    	    String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
    	    int ejercicioRepeticiones,boolean ejercicio_Pub_Priv, String ownerId) {
	jdbcTemplate.update(SqlConstants.UPDATE_EJERCICIO,
		new Object[] { ejercicioNombre, ejercicioTitulo,
	    	    ejercicioSubtitulo,ejercicioDescripcion,ejercicioEstado_Forma, 
	    	    ejercicioRepeticiones, ejercicio_Pub_Priv,ownerId,ej_id });

    }
    
    
    public void AsociateEjercicioDeRutina(int ej_id,int rut_id){
    	jdbcTemplate.update(SqlConstants.ASOCIATE_EJERCICIO_DE_RUTINA,
    			new Object[] { ej_id,rut_id});
    	
    }

    public List<Ejercicio> getEjercicio(String ownerId, int ej_id) {
	return jdbcTemplate.query(SqlConstants.GET_EJERCICIO,
		new Object[] { ownerId, ej_id},
		new EjercicioRowMapper());
    }

    public List<Ejercicio> getAllEjercicio(String ownerId, boolean ejercicioPub_Priv) {
    	
    	if(ejercicioPub_Priv==false)
    	{
    		//System.out.println(ejercicioPub_Priv);
    			return jdbcTemplate.query(SqlConstants.GET_ALL_EJERCICIO,
    				new Object[] { ownerId}, new EjercicioRowMapper());
    	}
    	else
    	{
    		//System.out.println(ejercicioPub_Priv);
    		return jdbcTemplate.query(SqlConstants.GET_ALL_EJERCICIO1,
    				new Object[] { ejercicioPub_Priv}, new EjercicioRowMapper());
    	}
    		
    }
    
    
    public List<Ejercicio> getAllEjerciciosDeRutina(int rut_id){  	
    	return jdbcTemplate.query(SqlConstants.GET_EJERCICIOS_DE_RUTINA,
    			new Object[] {rut_id}, new EjercicioRowMapper());  	
    }
    
    public List<Ejercicio> getAllEjerciciosNoDeRutina(int rut_id){
    	return jdbcTemplate.query(SqlConstants.GET_EJERCICIOS_NO_DE_RUTINA,
    			new Object[] { rut_id}, new EjercicioRowMapper());
    	
    }
    
    
    

    public void deleteEjercicio(String ownerId, int ej_id) {
	jdbcTemplate.update(SqlConstants.DELETE_EJERCICIO,
		new Object[] { ownerId, ej_id });
    }

    public void deleteAllEjercicio(String ownerId) {
	jdbcTemplate.update(SqlConstants.DELETE_ALL_EJERCICIO,
		new Object[] { ownerId});
    }
    
    // Inyección del dataSource mediante el constructor
    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void deleteEjercicioDeRutina(int rut_id, int ej_id) {
    	jdbcTemplate.update(SqlConstants.DELETE_EJERCICIO_DE_RUTINA,
    		new Object[] { rut_id, ej_id });
        }
    
    
    public EjercicioDaoImpl() {
    }
}
