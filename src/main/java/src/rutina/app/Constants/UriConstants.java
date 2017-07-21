package src.rutina.app.Constants;

/*
 * Clase que almacena los URIs accesibles del servicio REST.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

public class UriConstants {

    /* REGISTER Rutinas */
    public static final String USUARIOS_REGISTER = "/RutinaRegister//";
    
    /* OWNERS */
    public static final String USUARIO = "/Rutina_app/{user_id:.+}";
    public static final String ALL_USUARIOS = "/Rutina_app//";
    
    /* RUTINAS*/
    public static final String RUTINAS = "/Rutina_app/rutinas/{user_id:.+}/{rut_id:.+}";
    public static final String RUTINAS1= "/Rutina_app/rutinas/publicas/{user_id:.+}";
    public static final String RUTINAS_DOWNLOAD = "/Rutina_app/downloads/{user_id:.+}/{rut_id:.+}";
    public static final String ALL_RUTINAS = "/Rutina_app/rutinas/{user_id:.+}//";

    /* EJERCICIOS */
    public static final String EJERCICIOS = "/Rutina_app/ejercicios/{user_id:.+}/{ej_id:.+}";
    public static final String ALL_EJERCICIOS = "/Rutina_app/ejercicios/{user_id:.+}//";

    /* VIDEOS */
    //public static final String ALL_VIDEOS = "/Rutina_app/videos/{owner_id:.+}//";
    public static final String VIDEOS = "/Rutina_app/videos/{user_id:.+}/{ej_id:.+}";
    
    /*ASOCIACION DE EJERCICIOS A RUTINAS*/
    public static final String RUTINAS_EJERCICIOS = "/Rutina_app/rutinas/asociaciones/{rut_id:.+}/{ej_id:.+}";
    public static final String ALL_RUTINAS_EJERCICIOS = "/Rutina_app/rutinas/asociaciones/{rut_id:.+}//";
    public static final String ALL_RUTINAS_NO_EJERCICIOS= "/Rutina_app/rutinas/noasociaciones/{rut_id:.+}//";
    
    
    
}
