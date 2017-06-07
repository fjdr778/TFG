package src.rutina.app.Dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import src.rutina.app.Objects.Ejercicio;

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
	    int ejercicioRepeticiones, boolean ejercicio_Pub_Priv, String ownerId);
    
    public abstract void updateEjercicio(int ej_id, String ejercicioNombre, String ejercicioTitulo,
    	    String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
    	    int ejercicioRepeticiones,boolean ejercicio_Pub_Priv, String ownerId);
    
    
    public abstract void AsociateEjercicioDeRutina(int ej_id,int rut_id);
    
    public abstract List<Ejercicio> getEjercicio(String ownerId, int ej_id);
    public abstract List<Ejercicio> getAllEjercicio(String ownerId, boolean ejercicioPub_Priv);
    
    public abstract List<Ejercicio> getAllEjerciciosDeRutina(int rut_id);
    public abstract List<Ejercicio> getAllEjerciciosNoDeRutina(int rut_id);
    
    
    public abstract void deleteEjercicio(String ownerId, int ej_id);
    public abstract void deleteAllEjercicio(String ownerId);
    
    public abstract void deleteEjercicioDeRutina(int rut_id,int ej_id);
}