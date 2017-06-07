package src.rutina.app.Dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import src.rutina.app.Objects.Rutina;


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
	    String rutinaInfo_Rutina,boolean rutinaPub_Priv,String ownerId);
    public abstract List<Rutina> getRutina(String ownerId,int rut_id);
    public abstract List<Rutina> getAllRutinas(String ownerId, boolean rutinaPub_Priv,String rutina_busqueda);
    public abstract void deleteRutina(String ownerId,int rut_id);
    public abstract void deleteAllRutinas(String ownerId);
    public abstract void updateRutina(int rut_id, String rutinaNombre, String rutinaDescripcion,
    	    String rutinaInfo_Rutina,boolean rutinaPub_Priv,String ownerId);
}
