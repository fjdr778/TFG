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
    }

}