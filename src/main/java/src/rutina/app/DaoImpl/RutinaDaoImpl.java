package src.rutina.app.DaoImpl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import src.rutina.app.Constants.SqlConstants;
import src.rutina.app.Dao.RutinaDao;
import src.rutina.app.Objects.Rutina;
import src.rutina.app.RowMappers.RutinaRowMapper;



/*
 * Clase que implementa la interfaz DAO de Rutinas. Realiza las operaciones
 * necesarias sobre la base de datos relacionadas con las rutinas a petición
 * del controlador REST de rutinas. No se comentan los métodos debido a que
 * se corresponden con las operaciones del controlador, con lo que ya están
 * descritos previamente.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */

public class RutinaDaoImpl implements RutinaDao {
    
    JdbcTemplate jdbcTemplate;

    @Override
	public void createRutina(String rutinaNombre, String rutinaDescripcion, String rutinaInfo_Rutina,boolean rutinaPub_Priv,String ownerId) {
    	//System.out.println(rutinaNombre+rutinaDescripcion+rutinaInfo_Rutina+rutinaPub_Priv);
    	jdbcTemplate.update(SqlConstants.CREATE_RUTINA,
    	new Object[] { rutinaNombre, rutinaDescripcion, rutinaInfo_Rutina,rutinaPub_Priv, ownerId });
		
	}
    
    @Override
    public void updateRutina(int rut_id, String rutinaNombre, String rutinaDescripcion,
    	    String rutinaInfo_Rutina,boolean rutinaPub_Priv,String ownerId) {
    	
    	System.out.println("DaoImplUpdate:"+ rut_id+rutinaNombre+rutinaDescripcion+rutinaInfo_Rutina+rutinaPub_Priv+ownerId);
    	jdbcTemplate.update(SqlConstants.UPDATE_RUTINA,
    		new Object[] { rut_id,rutinaNombre, rutinaDescripcion,rutinaInfo_Rutina,rutinaPub_Priv,ownerId});
        }



    @Override
	public List<Rutina> getRutina(String ownerId, int rut_id) {
		return jdbcTemplate.query(SqlConstants.GET_RUTINA,
				new Object[] { ownerId,rut_id },
				new RutinaRowMapper());
	}
    
    @Override
	public List<Rutina> getRutina1(int rut_id) {
		return jdbcTemplate.query(SqlConstants.GET_RUTINA1,
				new Object[] {rut_id },
				new RutinaRowMapper());
	}
    
    @Override
	public List<Rutina> getAllRutinas(String ownerId,boolean rutinaPub_Priv,String rutina_busqueda) {	
    	
    	if(rutinaPub_Priv==false)
    	{
    		if(rutina_busqueda=="")
    		{
    			System.out.println("Privado");	  		
    			return jdbcTemplate.query("SELECT rut_id,Nombre,Descripcion,Info_Rutina,Pub_priv FROM RUTINA WHERE USUARIOS_Email=?",
    					new Object[] {ownerId},
    					new RutinaRowMapper()) ;
    		}
    		else
    		{
    			System.out.println("Privado");	  		
    			return jdbcTemplate.query("SELECT rut_id,Nombre,Descripcion,Info_Rutina,Pub_priv FROM RUTINA WHERE USUARIOS_Email=? AND Descripcion LIKE '%" + rutina_busqueda +
    					"%' OR Nombre LIKE '%" + rutina_busqueda +"%' ",
    					new Object[] {ownerId},
    					new RutinaRowMapper()) ;
    		}   		 		
    	}
    	else
    	{  		
    		if(rutina_busqueda=="")
    		{	
    			return jdbcTemplate.query("SELECT rut_id,Nombre,Descripcion,Info_Rutina,Pub_priv FROM RUTINA WHERE Pub_priv=?",
        				new Object[] {rutinaPub_Priv},
        				new RutinaRowMapper()) ;
    		}
    		else
    		{
    		System.out.println("Publico");	  

    			return jdbcTemplate.query("SELECT rut_id,Nombre,Descripcion,Info_Rutina,Pub_priv FROM RUTINA WHERE Pub_priv=? AND Descripcion LIKE '%" + rutina_busqueda + 
    				"%' OR Nombre LIKE '%" + rutina_busqueda +"%' ",
    				new Object[] {rutinaPub_Priv},
    				new RutinaRowMapper()) ;	
    		}
    		
    		
    	}
	}
    
    
    @Override
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
    }

}