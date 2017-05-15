package src.rutina.app;

import java.util.List;
import java.sql.Time;
import java.sql.Date;

/*
 * Clase que representa la interfaz DAO de eventos. Abstrae las operaciones
 * necesarias sobre la base de datos relacionadas con los eventos a petición
 * del controlador REST de eventos.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */


public abstract interface EjercicioDao {
    // No se le pasa el identificador de ejercicio para crearlo porque en la base 
    // de datos es un campo con autoincremento
	
    public abstract void createEjercicio(String ejercicioNombre, String ejercicioTitulo,
	    String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
	    int ejercicioRepeticiones,int ejercicioRep_Video, String ownerId);
    
    public abstract void updateEjercicio(int ej_id, String ejercicioNombre, String ejercicioTitulo,
    	    String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
    	    int ejercicioRepeticiones,int ejercicioRep_Video, String ownerId);
    
    public abstract List<Ejercicio> getEjercicio(String ownerId, int ej_id);
    public abstract List<Ejercicio> getAllEjercicio(String ownerId);
    public abstract void deleteEjercicio(String ownerId, int ej_id);
    public abstract void deleteAllEjercicio(String ownerId);
}