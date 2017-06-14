package src.rutina.app.Constants;

import java.sql.Date;

import src.rutina.app.RowMappers.EjercicioRowMapper;

/*
 * Clase que almacena las cadenas MySQL de consulta a Base de Datos 
 * relacionadas con cada una de las implementaciones DAO de las rutinas y ejercicios.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */

public class SqlConstants {
    
	/* AUTHENTICATION */
    public static final String GET_USER_AUTHENTICATION = 
	    "SELECT Email,Password,Enabled FROM USUARIOS WHERE Email=?";
    public static final String GET_USER_AUTHORITY = 
	    "SELECT USUARIOS_Email,Role FROM USUARIOS_ROLES WHERE USUARIOS_email=?";
    
    
    /* USUARIOS - USUARIOS_INFO - USUARIOS_ROLES */
    public static final String CREATE_USUARIO = 
	    "INSERT INTO USUARIOS (Email,Password,Enabled) VALUES (?,?,?)";
    public static final String CREATE_USUARIO_INFO =
	    "INSERT INTO USUARIOS_INFO (USUARIOS_Email,Nombre,Telefono,Fecha_Nacimiento) VALUES (?,?,?,?)";
    public static final String CREATE_USUARIO_ROLE =
	    "INSERT INTO USUARIOS_ROLES (USUARIOS_Email,Role) VALUES (?,?)";
    
    
    
    public static final String UPDATE_USUARIO = 
	    "UPDATE USUARIOS SET Password=? WHERE Email=?";
    public static final String UPDATE_USUARIO_INFO = 
	    "UPDATE USUARIOS_INFO SET Nombre=?, Telefono=?, Fecha_Nacimiento=? WHERE USUARIOS_Email=?";
    
    // No se actualiza el rol, operación que queda 
    // por implementar para futura interfaz de gestión
    
    public static final String GET_USUARIO_INFO = 
	    "SELECT USUARIOS_Email,Nombre,Telefono,Fecha_Nacimiento FROM USUARIOS_INFO WHERE USUARIOS_Email=?";
    public static final String GET_ALL_USUARIOS_INFO = 
	    "SELECT USUARIOS_Email,Nombre,Telefono,Fecha_Nacimiento FROM USUARIOS_INFO";
    public static final String DELETE_USUARIO = 
	    "DELETE FROM USUARIOS WHERE Email=?";
    public static final String DELETE_USUARIO_INFO = 
	    "DELETE FROM USUARIOS_INFO WHERE USUARIOS_Email=?";
    public static final String DELETE_USUARIO_ROLE =
	    "DELETE FROM USUARIOS_ROLES WHERE USUARIOS_Email=?";
    
    
    public static final String DELETE_USUARIO_ALL_RUTINAS = 
	    "DELETE FROM RUTINA WHERE USUARIOS_Email=?";
    public static final String DELETE_USUARIO_ALL_EJERCICIOS = 
	    "DELETE FROM EJERCICIO WHERE RUTINA_USUARIOS_Email=?";
    
    public static final String DELETE_ALL_USUARIOS = 
	    "DELETE FROM USUARIOS";
    public static final String DELETE_ALL_USUARIOS_INFO = 
	    "DELETE FROM USUARIOS_INFO";
    public static final String DELETE_ALL_USUARIOS_ROLE =
	    "DELETE FROM USUARIOS_ROLES";
    
    public static final String DELETE_ALL_USUARIOS_ALL_RUTINAS = 
	    "DELETE FROM RUTINA";
    public static final String DELETE_ALL_USUARIOS_ALL_EJERCICIOS = 
	    "DELETE FROM EJERCICIO";
    
    
    /* RUTINAS */
    public static final String CREATE_RUTINA = 
	    "INSERT INTO RUTINA (Nombre,Descripcion,Info_Rutina,Pub_priv,USUARIOS_Email) VALUES (?,?,?,?,?)"; 
    
    public static final String UPDATE_RUTINA = 
    	"UPDATE RUTINA SET Nombre=?,Descripcion=?,Info_Rutina=?,Pub_priv=? WHERE rut_id=? AND USUARIOS_Email=? ";
    
    //Obtener rutinas de un usuario
    public static final String GET_RUTINA = 
	    "SELECT rut_id,Nombre,Descripcion,Info_Rutina,Pub_priv FROM RUTINA WHERE USUARIOS_Email=? AND rut_id=?";
    
  //Obtener rutinas de un usuario
    public static final String GET_RUTINA1 = 
	    "SELECT rut_id,Nombre,Descripcion,Info_Rutina,Pub_priv FROM RUTINA WHERE rut_id=?";
    
   //Los dos siguientes estan impoementados directamente en RutinaDaoImpl!! 
    
   /* public static final String GET_ALL_RUTINAS = 
	    "SELECT rut_id,Nombre,Descripcion,Info_Rutina,Pub_priv FROM RUTINA WHERE USUARIOS_Email=? AND Descripcion LIKE '%dfg%'";
    
    public static final String GET_ALL_RUTINAS1 = 
    	    "SELECT rut_id,Nombre,Descripcion,Info_Rutina,Pub_priv FROM RUTINA WHERE Pub_priv=? AND Descripcion LIKE '%%'";
   */
  //Eliminar rutinas de un usuario
    public static final String DELETE_RUTINA = 
	    "DELETE FROM RUTINA WHERE USUARIOS_Email=? AND rut_id=?";
    public static final String DELETE_ALL_RUTINAS = 
	    "DELETE FROM RUTINA WHERE USUARIOS_Email=?";


    /* EJERCICIOS */
    public static final String CREATE_EJERCICIO = 
	    "INSERT INTO EJERCICIO (Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email) VALUES (?,?,?,?,?,?,?,?)"; 
    
    public static final String UPDATE_EJERCICIO = 
    	"UPDATE EJERCICIO SET Nombre=?, Titulo=?, Subtitulo=?,Descripcion=?,Estado_forma=?,Repeticiones=?,Pub_priv=? WHERE RUTINA_USUARIOS_Email=? AND ej_id=?";
    
    //Obtener rutinas de un usuario
    public static final String GET_EJERCICIO = 
	    "SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email FROM EJERCICIO WHERE RUTINA_USUARIOS_Email=? AND ej_id=?";
    
    
    public static final String GET_ALL_EJERCICIO = 
	    "SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email FROM EJERCICIO WHERE RUTINA_USUARIOS_Email=?";
    
    public static final String GET_ALL_EJERCICIO1 =
    		"SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email FROM EJERCICIO WHERE Pub_priv=?";
    
  //Eliminar rutinas de un usuario
    public static final String DELETE_EJERCICIO = 
	    "DELETE FROM EJERCICIO WHERE RUTINA_USUARIOS_Email=? AND ej_id=?";
    public static final String DELETE_ALL_EJERCICIO = 
	    "DELETE FROM EJERCICIO WHERE RUTINA_USUARIOS_Email=?";
    
    
    /*ASOCICACION DE EJERCICIOS A RUTINAS*/
    
    public static final String ASOCIATE_EJERCICIO_DE_RUTINA = 
    		"INSERT INTO EJERCICIO_has_RUTINA (EJERCICIO_ej_id,RUTINA_rut_id) VALUES (?,?)";
    
    public static final String GET_EJERCICIO_DE_RUTINA = 
        		"SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email FROM EJERCICIO,EJERCICIO_has_RUTINA"
    		+ "WHERE EJERCICIO.ej_id=EJERCICIO_has_RUTINA.EJERCICIO_ej_id";
    
    
    //Va a devolver un objeto tipo ejercicio. Array de Ejercicios. Todos los ejercicios asociados a una rutina.
    public static final String GET_EJERCICIOS_DE_RUTINA = 
    		"SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email FROM "
    		+ "EJERCICIO,EJERCICIO_has_RUTINA WHERE EJERCICIO_has_RUTINA.RUTINA_rut_id=? AND EJERCICIO_has_RUTINA.EJERCICIO_ej_id=EJERCICIO.ej_id";
    
    
  //Va a devolver un objeto tipo ejercicio. Array de Ejercicios. Todos los ejercicios asociados a una rutina PUBLICA EXCEPTO LOS PRIVADOS.
    public static final String GET_EJERCICIOS_DE_RUTINA_PUBLICA = 
    		"SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email FROM "
    		+ "EJERCICIO,EJERCICIO_has_RUTINA WHERE EJERCICIO_has_RUTINA.RUTINA_rut_id=? AND EJERCICIO_has_RUTINA.EJERCICIO_ej_id=EJERCICIO.ej_id AND EJERCICIO.Pub_priv=1";
    
    
  //Va a devolver un objeto tipo ejercicio. Array de Ejercicios. Todos los ejercicios que noe estan asociados a dicha rutina.
    
    /*public static final String GET_EJERCICIOS_NO_DE_RUTINA =
    		"select ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv from EJERCICIO WHERE EJERCICIO.ej_id NOT IN "
    		+ "(SELECT EJERCICIO_has_RUTINA.EJERCICIO_ej_id FROM EJERCICIO_has_RUTINA WHERE RUTINA_rut_id=?) AND EJERCICIO.Pub_priv=1";*/
    
    
    /*public static final String GET_EJERCICIOS_NO_DE_RUTINA =    
    			"SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email from EJERCICIO WHERE EJERCICIO.Pub_priv=0 AND RUTINA_USUARIOS_Email=?"
    			+ "AND EJERCICIO.ej_id NOT IN (SELECT EJERCICIO_has_RUTINA.EJERCICIO_ej_id FROM EJERCICIO_has_RUTINA WHERE RUTINA_rut_id=?)"
    			+ "UNION"
    			+ "SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Pub_priv,RUTINA_USUARIOS_Email from EJERCICIO WHERE EJERCICIO.Pub_priv=1"
    			+ "AND EJERCICIO.ej_id NOT IN (SELECT EJERCICIO_has_RUTINA.EJERCICIO_ej_id FROM EJERCICIO_has_RUTINA WHERE RUTINA_rut_id=?)";
    			/*+ "EJERCICIO.ej_id NOT IN ("
    		+ "SELECT EJERCICIO_has_RUTINA.EJERCICIO_ej_id FROM EJERCICIO_has_RUTINA,EJERCICIO WHERE RUTINA_rut_id=? AND)";*/
    
    
    
    public static final String DELETE_EJERCICIO_DE_RUTINA = 
    		"DELETE FROM EJERCICIO_has_RUTINA WHERE RUTINA_rut_id=? AND EJERCICIO_ej_id=?";

    public static final String GET_RUTINAS_DE_EJERCICIOS = 
    		"SELECT EJERCICIO_rut_id  FROM EJERCICIO_has_RUTINA WHERE RUTINA_ej_id=?";
    

    
    public static final String DELETE_ALL_EJERCICIOS_DE_RUTINA =
    		"DELETE FROM EJERCICIO_has_RUTINA WHERE RUTINA_rut_id=?";
    
  
    /* VIDEOS */  
    
    public static final String CREATE_VIDEO =
    		"INSERT VIDEOS (Nombre,URL,VIDEOS_EJERCICIOS_ej_id,USUARIOS_Email) VALUES (?,?,?,?)";
    
    public static final String UPDATE_VIDEO =
    	"UPDATE VIDEOS SET Nombre=?,URL=?,VIDEOS_EJERCICIOS_ej_id=?,USUARIOS_Email=? WHERE VIDEOS_EJERCICIOS_ej_id=? AND USUARIOS_Email=?";
    public static final String GET_VIDEO =
    		"SELECT Nombre,URL,VIDEOS_EJERCICIOS_ej_id,USUARIOS_Email  FROM VIDEOS WHERE VIDEOS_EJERCICIOS_ej_id =? AND USUARIOS_Email=?";
    
    public static final String GET_ALL_VIDEOS = 
    		"SELECT Nombre,URL,VIDEOS_EJERCICIOS_ej_id,USUARIOS_Email  FROM VIDEOS WHERE USUARIOS_Email=?";
    
    public static final String DELETE_VIDEO =
    		"DELETE FROM VIDEOS WHERE VIDEOS_EJERCICIOS_ej_id =? AND USUARIOS_Email=?";
    
    public static final String DELETE_ALL_VIDEOS =
    		"DELETE FROM VIDEOS WHERE USUARIOS_Email=?";
    		
}
