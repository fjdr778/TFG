package src.rutina.app;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


/*
 * Clase Abstracta que representa la interfaz DAO de rutinas. Abstrae las operaciones
 * necesarias sobre la base de datos relacionadas con las rutinas a petición
 * del controlador REST de rutinas.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */

public abstract interface RutinaDao {

    public abstract void createRutina(String rutinaNombre, String rutinaDescripcion,
	    String rutinaInfo_Rutina,String ownerId);
   // public abstract void updateRutina(int rut_id,String rutinaNombre, String rutinaDescripcion,
    	   // String rutinaInfo_rutina);
    public abstract List<Rutina> getRutina(String ownerId,int rut_id);
    public abstract List<Rutina> getAllRutinas(String ownerId);
    public abstract void deleteRutina(String ownerId,int rut_id);
    public abstract void deleteAllRutinas(String ownerId);
    public abstract void updateRutina(int rut_id, String rutinaNombre, String rutinaDescripcion,
    	    String rutinaInfo_Rutina,String ownerId);
}
