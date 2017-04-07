package src.rutina.app;

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
	    "UPDATE USUARIOS_INFO SET Name=?, Telefono=?, Fecha_Nacimiento=? WHERE USUARIOS_Email=?";
    
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
	    "INSERT INTO RUTINA (Nombre,Descripcion,Info_Rutina,USUARIOS_Email) VALUES (?,?,?,?)"; 
    
    public static final String UPDATE_RUTINA = 
    	"UPDATE RUTINA SET Nombre=?,Descripcion=?, Info_Rutina=? WHERE USUARIOS_Email=? AND rut_id=?";
    
    //Obtener rutinas de un usuario
    public static final String GET_RUTINA = 
	    "SELECT rut_id,Nombre,Descripcion,Info_Rutina FROM RUTINA WHERE USUARIOS_Email=? AND rut_id=?";
    public static final String GET_ALL_RUTINAS = 
	    "SELECT rut_id,Nombre,Descripcion,Info_Rutina FROM RUTINA WHERE USUARIOS_Email=?";
  //Eliminar rutinas de un usuario
    public static final String DELETE_RUTINA = 
	    "DELETE FROM RUTINA WHERE USUARIOS_Email=? AND rut_id=?";
    public static final String DELETE_ALL_RUTINAS = 
	    "DELETE FROM RUTINA WHERE USUARIOS_Email=?";


    
    /* EJERCICIOS */
    public static final String CREATE_EJERCICIO = 
	    "INSERT INTO EJERCICIO (Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Rep_video,RUTINA_rut_id) VALUES (?,?,?,?,?,?,?,?)";
    
    public static final String UPDATE_EJERCICIO = 
	    "UPDATE EJERICIO SET Nombre=?, Titulo=?, Subtitulo=?, Descripcion=?, Estado_forma=?, Repeticiones=?, Rep_video=? RUTINA_rut_id=? WHERE ej_id=? AND RUTINA_rut_id=?";
    
    public static final String GET_EJERCICIO = 
	    "SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Rep_video,RUTINA_rut_id FROM EJERCICIO WHERE ej_id=? AND RUTINA_rut_id=?";
    public static final String GET_ALL_EJERCICIOS = 
	    "SELECT ej_id,Nombre,Titulo,Subtitulo,Descripcion,Estado_forma,Repeticiones,Rep_video,RUTINA_rut_id FROM EJERCICIO WHERE RUTINA_rut_id=?";
    
    public static final String DELETE_EJERCICIO = 
	    "DELETE FROM EJERCICIO WHERE RUTINA_rut_id=? AND ej_id=?";
    public static final String DELETE_ALL_EJERCICIOS = 
	    "DELETE FROM EJERCICIO WHERE RUTINA_rut_id=?";

}
