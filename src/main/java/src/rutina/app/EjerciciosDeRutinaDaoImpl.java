package src.rutina.app;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

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

public class EjerciciosDeRutinaDaoImpl implements EjerciciosDeRutinaDao {
    
    JdbcTemplate jdbcTemplate;
    
    public void createAsociacionEjercicioRutina(int ej_id, int rut_id){
	jdbcTemplate.update(SqlConstants.CREATE_EJERCICIO_DE_RUTINA,
		new Object[] { ej_id, rut_id });
    }

    
    public List<EjerciciosDeRutina> getAllEjerciciosDeRutina(int rut_id){
    	return jdbcTemplate.query(SqlConstants.GET_EJERCICIOS_DE_RUTINA,
    			new Object[] {rut_id},
    			new EjerciciosDeRutinaRowMapper());
    } //Todos los Ejercicios de una rutina
    
    public List<EjerciciosDeRutina> getEjercicioDeRutina(int ej_id,int rut_id){
    	return jdbcTemplate.query(SqlConstants.GET_EJERCICIO_DE_RUTINA,
    			new Object[] {ej_id,rut_id},
    			new EjerciciosDeRutinaRowMapper());
    }
    
  /*  public List<EjerciciosDeRutina> getRutinasDeEjercicio(int ej_id){
    	return jdbcTemplate.query(SqlConstants.GET_RUTINAS_DE_EJERCICIOS,
    			new Object[] { ej_id},
    			new EjerciciosDeRutinaRowMapper());	
    } //Todas las Rutinas de un ejercicio
    */

    
    public void deleteEjercicioDeRutina(int ej_id,int rut_id){
    	jdbcTemplate.update(SqlConstants.DELETE_EJERCICIO_DE_RUTINA,
    			new Object[] {rut_id, ej_id });
    	
    }
    
    public void deleteAllEjercicio(int rut_id){
    	jdbcTemplate.update(SqlConstants.DELETE_ALL_EJERCICIOS_DE_RUTINA,
    			new Object[] {rut_id});
    	
    }
    
    
    // Inyección del dataSource mediante el constructor
    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public EjerciciosDeRutinaDaoImpl() {
    }


	
}
