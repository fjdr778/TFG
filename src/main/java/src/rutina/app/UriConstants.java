package src.rutina.app;

/*
 * Clase que almacena los URIs accesibles del servicio REST.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */

public class UriConstants {

    /* REGISTER Rutinas */
    public static final String USUARIOS_REGISTER = "/RutinaRegister//";
    
    /* OWNERS */
    public static final String USUARIO = "/Rutina_app/{owner_id:.+}";
    public static final String ALL_USUARIOS = "/Rutina_app//";
    
    /* RUTINAS*/
    public static final String RUTINAS = "/Rutina_app/rutinas/{owner_id:.+}/{rut_id:.+}";
    public static final String ALL_RUTINAS = "/Rutina_app/rutinas/{owner_id:.+}//";

    /* EJERCICIOS */
    public static final String EJERCICIOS = "/Rutina_app/ejercicios/{owner_id:.+}/{ej_id:.+}";
    public static final String ALL_EJERCICIOS = "/Rutina_app/ejercicios/{owner_id:.+}//";

    /*ASOCIACION DE EJERCICIOS A RUTINAS*/
    public static final String RUTINAS_EJERCICIOS = "/Rutina_app/rutinas/asociaciones/{rut_id:.+}/{ej_id:.+}";
    public static final String ALL_RUTINAS_EJERCICIOS = "/Rutina_app/rutinas/asociaciones/{rut_id:.+}//";
}
