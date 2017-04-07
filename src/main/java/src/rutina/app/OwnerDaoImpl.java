package src.rutina.app;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * Clase que implementa la interfaz DAO de propietarios. Realiza las 
 * operaciones necesarias sobre la base de datos relacionadas con los 
 * propietarios a petición del controlador REST de propietarios. No se 
 * comentan los métodos debido a que se corresponden con las operaciones 
 * del controlador, con lo que ya están descritos previamente.
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */

public class OwnerDaoImpl implements OwnerDao {

    JdbcTemplate jdbcTemplate;

    public void createOwner(String ownerId, String ownerName,
	    String ownerPhoneNumber, Date ownerBirthDate, 
	    String ownerPassw) {
	// Por defecto, el usuario está habilitado (enabled)
	jdbcTemplate.update(SqlConstants.CREATE_USUARIO,
			new Object[] { ownerId, ownerPassw, 1 });
	// No se introduce la contraseña aquí
	jdbcTemplate.update(SqlConstants.CREATE_USUARIO_INFO,
		new Object[] { ownerId, ownerName, ownerPhoneNumber,
			ownerBirthDate });
	
	// Por defecto, el rol es ROLE_USER
	jdbcTemplate.update(SqlConstants.CREATE_USUARIO_ROLE,
		new Object[] { ownerId, "ROLE_USER" });
    }
    
    public void updateOwner(String ownerId, String ownerName,
	    String ownerPhoneNumber, Date ownerBirthDate, 
	    String ownerPassw) {
	jdbcTemplate.update(SqlConstants.UPDATE_USUARIO,
			new Object[] { ownerPassw, ownerId });
	jdbcTemplate.update(SqlConstants.UPDATE_USUARIO_INFO,
		new Object[] { ownerName, ownerPhoneNumber,
			ownerBirthDate, ownerId });
	// No actualizamos el rol del usuario, ya que es cosa del administrador
    }

    public List<Owner> getOwner(String ownerId) {
	return jdbcTemplate.query(SqlConstants.GET_USUARIO_INFO,
		new Object[] { ownerId }, new OwnerRowMapper());
    }

    public List<Owner> getAllOwners() {
	return jdbcTemplate.query(SqlConstants.GET_ALL_USUARIOS_INFO,
		new OwnerRowMapper());
    }

    public void deleteOwner(String ownerId) {
	jdbcTemplate.update(SqlConstants.DELETE_USUARIO_ALL_EJERCICIOS,
		new Object[] { ownerId });
	jdbcTemplate.update(SqlConstants.DELETE_USUARIO_ALL_RUTINAS,
		new Object[] { ownerId });
	jdbcTemplate.update(SqlConstants.DELETE_USUARIO_ROLE,
		new Object[] { ownerId });
	jdbcTemplate.update(SqlConstants.DELETE_USUARIO_INFO,
		new Object[] { ownerId });
	jdbcTemplate.update(SqlConstants.DELETE_USUARIO,
		new Object[] { ownerId });
    }

    public void deleteAllOwners() {
	jdbcTemplate.update(SqlConstants.DELETE_ALL_USUARIOS_ALL_EJERCICIOS);
	jdbcTemplate.update(SqlConstants.DELETE_ALL_USUARIOS_ALL_RUTINAS);
	jdbcTemplate.update(SqlConstants.DELETE_ALL_USUARIOS_ROLE);
	jdbcTemplate.update(SqlConstants.DELETE_ALL_USUARIOS_INFO);
	jdbcTemplate.update(SqlConstants.DELETE_ALL_USUARIOS);
    }
    
    // Inyección del dataSource mediante el constructor
    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public OwnerDaoImpl() {
    }
}
