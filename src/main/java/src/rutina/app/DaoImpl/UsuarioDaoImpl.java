package src.rutina.app.DaoImpl;

import java.sql.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import src.rutina.app.Constants.SqlConstants;
import src.rutina.app.Dao.UsuarioDao;
import src.rutina.app.Objects.Usuario;
import src.rutina.app.RowMappers.UsuarioRowMapper;

/*
 * Clase que implementa la interfaz DAO de usuarios. Realiza las 
 * operaciones necesarias sobre la base de datos relacionadas con los 
 * usuarios a petición del controlador REST de usuarios. No se 
 * comentan los métodos debido a que se corresponden con las operaciones 
 * del controlador, con lo que ya están descritos previamente.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

public class UsuarioDaoImpl implements UsuarioDao {

	JdbcTemplate jdbcTemplate;

	@Override
	public void createUser(String userId, String userName,
			String userPhoneNumber, Date userBirthDate, 
			String userPassw) {
		// Por defecto, el usuario está habilitado (enabled)
		jdbcTemplate.update(SqlConstants.CREATE_USUARIO,
				new Object[] { userId, userPassw, 1 });
		// No se introduce la contraseña aquí
		jdbcTemplate.update(SqlConstants.CREATE_USUARIO_INFO,
				new Object[] { userId, userName, userPhoneNumber,
						userBirthDate });

		// Por defecto, el rol es ROLE_USER
		jdbcTemplate.update(SqlConstants.CREATE_USUARIO_ROLE,
				new Object[] { userId, "ROLE_USER" });
	}

	@Override
	public void updateUser(String userId, String userName,
			String userPhoneNumber, Date userBirthDate, 
			String userPassw) {
		jdbcTemplate.update(SqlConstants.UPDATE_USUARIO,
				new Object[] { userPassw, userId });
		jdbcTemplate.update(SqlConstants.UPDATE_USUARIO_INFO,
				new Object[] { userName, userPhoneNumber,
						userBirthDate, userId });
		// No actualizamos el rol del usuario, ya que es cosa del administrador
	}

	@Override
	public List<Usuario> getUser(String userId) {
		return jdbcTemplate.query(SqlConstants.GET_USUARIO_INFO,
				new Object[] { userId }, new UsuarioRowMapper());
	}

	@Override
	public List<Usuario> getAllUsers() {
		return jdbcTemplate.query(SqlConstants.GET_ALL_USUARIOS_INFO,
				new UsuarioRowMapper());
	}

	@Override
	public void deleteUser(String userId) {

		jdbcTemplate.update(SqlConstants.DELETE_USUARIO_ALL_ASOCACIONES_USUARIO,
				new Object[] {userId});

		jdbcTemplate.update(SqlConstants.DELETE_USUARIO_ALL_VIDEOS,
				new Object[] { userId });  	
		jdbcTemplate.update(SqlConstants.DELETE_USUARIO_ALL_EJERCICIOS,
				new Object[] { userId });
		jdbcTemplate.update(SqlConstants.DELETE_USUARIO_ALL_RUTINAS,
				new Object[] { userId });
		jdbcTemplate.update(SqlConstants.DELETE_USUARIO_ROLE,
				new Object[] { userId });
		jdbcTemplate.update(SqlConstants.DELETE_USUARIO_INFO,
				new Object[] { userId });
		jdbcTemplate.update(SqlConstants.DELETE_USUARIO,
				new Object[] { userId });
	}

	@Override
	public void deleteAllUsers() {
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

	public UsuarioDaoImpl() {
	}
}
