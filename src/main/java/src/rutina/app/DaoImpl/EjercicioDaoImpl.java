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
 * Clase que implementa la interfaz DAO de ejercicios. Realiza las operaciones
 * necesarias sobre la base de datos relacionadas con los ejercicios a petición
 * del controlador REST de ejercicios. No se comentan los métodos debido a que
 * se corresponden con las operaciones del controlador, con lo que ya están
 * descritos previamente
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * version 2.0.0
 *
 */

public class EjercicioDaoImpl implements EjercicioDao {

	JdbcTemplate jdbcTemplate;

	public void createEjercicio(String ejercicioNombre, String ejercicioTitulo,
			String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
			int ejercicioRepeticiones, boolean ejercicio_Pub_Priv,String userId) {
		
		jdbcTemplate.update(SqlConstants.CREATE_EJERCICIO,
				new Object[] { ejercicioNombre, ejercicioTitulo,
						ejercicioSubtitulo,ejercicioDescripcion,ejercicioEstado_Forma, 
						ejercicioRepeticiones,ejercicio_Pub_Priv,userId });
	}

	public void updateEjercicio(int ej_id, String ejercicioNombre, String ejercicioTitulo,
			String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
			int ejercicioRepeticiones,boolean ejercicio_Pub_Priv, String userId) {
		
		jdbcTemplate.update(SqlConstants.UPDATE_EJERCICIO,
				new Object[] { ejercicioNombre, ejercicioTitulo,
						ejercicioSubtitulo,ejercicioDescripcion,ejercicioEstado_Forma, 
						ejercicioRepeticiones, ejercicio_Pub_Priv,userId,ej_id });

	}

	public void AsociateEjercicioDeRutina(int ej_id,int rut_id,String userId){
		
		jdbcTemplate.update(SqlConstants.ASOCIATE_EJERCICIO_DE_RUTINA,
				new Object[] { ej_id,rut_id,userId});

	}

	public List<Ejercicio> getEjercicio(String userId, int ej_id) {
		
		return jdbcTemplate.query(SqlConstants.GET_EJERCICIO,
				new Object[] { userId, ej_id},
				new EjercicioRowMapper());
	}

	public List<Ejercicio> getAllEjercicio(String userId, boolean ejercicioPub_Priv,String ejercicio_busqueda) {

		if(ejercicioPub_Priv==false)
		{
			if(ejercicio_busqueda=="")
			{				
				return jdbcTemplate.query(SqlConstants.GET_ALL_EJERCICIO,
						new Object[] { userId}, new EjercicioRowMapper());
			}
			else 
			{
				return jdbcTemplate.query("SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email FROM EJERCICIO WHERE RUTINA_USUARIOS_Email=? AND Descripcion LIKE '%" +
						ejercicio_busqueda +"%' OR Nombre LIKE '%" + ejercicio_busqueda +"%' OR Titulo LIKE '%" + ejercicio_busqueda +"%' OR Subtitulo LIKE '%" + ejercicio_busqueda +"%' ",
						new Object[] { userId}, new EjercicioRowMapper());
			}

		}
		else
		{
			if(ejercicio_busqueda=="")
			{				
				return jdbcTemplate.query(SqlConstants.GET_ALL_EJERCICIO1,
						new Object[] { ejercicioPub_Priv}, new EjercicioRowMapper());
			}
			else
			{
				return jdbcTemplate.query("SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email FROM EJERCICIO WHERE Pub_priv=1 AND (Descripcion LIKE '%" + 
						ejercicio_busqueda + "%' OR Nombre LIKE '%" + ejercicio_busqueda +"%' OR Titulo LIKE '%" + ejercicio_busqueda +"%' OR Subtitulo LIKE '%" + ejercicio_busqueda +"%')",
						new Object[] {}, new EjercicioRowMapper());
			}
		}

	}

	public List<Ejercicio> getAllEjerciciosDeRutina(int rut_id){  	

		return jdbcTemplate.query(SqlConstants.GET_EJERCICIOS_DE_RUTINA,
				new Object[] {rut_id}, new EjercicioRowMapper());  	
	}

	public List<Ejercicio> getEjerciciosDeRutinaPublica(int rut_id, String rutPub){ 

		return jdbcTemplate.query(SqlConstants.GET_EJERCICIOS_DE_RUTINA_PUBLICA,			
				new Object[] {rut_id}, new EjercicioRowMapper());  	
	}

	public List<Ejercicio> getAllEjerciciosNoDeRutina(int rut_id, String userId,String ejercicio_busqueda){
		if(ejercicio_busqueda.equals(""))
		{
			System.out.println("vacio: "+ejercicio_busqueda);
			return jdbcTemplate.query("SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email from EJERCICIO WHERE EJERCICIO.Pub_priv=0 AND RUTINA_USUARIOS_Email='"+ userId +
					"' AND EJERCICIO.ej_id NOT IN (SELECT EJERCICIO_has_RUTINA.EJERCICIO_ej_id FROM EJERCICIO_has_RUTINA WHERE RUTINA_rut_id="+rut_id+ ")"
					+ "UNION SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email from EJERCICIO WHERE EJERCICIO.Pub_priv=1" +
					" AND EJERCICIO.ej_id NOT IN (SELECT EJERCICIO_has_RUTINA.EJERCICIO_ej_id FROM EJERCICIO_has_RUTINA WHERE RUTINA_rut_id="+rut_id+ ")"
					,
					new Object[] {}, new EjercicioRowMapper());  	
		}
		else
		{
			System.out.println("lleno: "+ejercicio_busqueda);
			return jdbcTemplate.query("select * from (select ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email from EJERCICIO WHERE EJERCICIO.ej_id NOT IN "
					+ "(SELECT EJERCICIO_has_RUTINA.EJERCICIO_ej_id FROM EJERCICIO_has_RUTINA WHERE RUTINA_rut_id=?) AND Pub_priv=1 UNION "
					+ "select ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email from EJERCICIO WHERE RUTINA_USUARIOS_Email=?" 
					+ "AND Pub_priv=0) as tab WHERE tab.Descripcion LIKE '%" + 
					ejercicio_busqueda + "%' OR tab.Nombre LIKE '%" + ejercicio_busqueda +"%' OR tab.Titulo LIKE '%" + ejercicio_busqueda +"%' "
					+ "OR tab.SubTitulo LIKE '%" + ejercicio_busqueda +"%'",
					new Object[] { rut_id,userId}, new EjercicioRowMapper());  	
		}
	}

	public void deleteEjercicio(String userId, int ej_id) {
		
		jdbcTemplate.update(SqlConstants.DELETE_EJERCICIO,
				new Object[] { userId, ej_id });
	}

	public void deleteAllEjercicio(String userId) {
		
		jdbcTemplate.update(SqlConstants.DELETE_ALL_EJERCICIO,
				new Object[] { userId});
	}

	public void deleteEjerciciosDeRutina(int rut_id){
		
		jdbcTemplate.update(SqlConstants.DELETE_ALL_EJERCICIOS_DE_RUTINA,
				new Object[] { rut_id});

	}

	public void deleteEjercicioDeRutina(int rut_id, int ej_id) {
		
		jdbcTemplate.update(SqlConstants.DELETE_EJERCICIO_DE_RUTINA,
				new Object[] { rut_id, ej_id });
	}
	
	// Inyección del dataSource mediante el constructor
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EjercicioDaoImpl() {
	}
}
