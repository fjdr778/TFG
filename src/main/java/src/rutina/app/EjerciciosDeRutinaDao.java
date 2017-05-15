package src.rutina.app;

import java.util.List;

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


public abstract interface EjerciciosDeRutinaDao {
    // No se le pasa el identificador de ejercicio para crearlo porque en la base 
    // de datos es un campo con autoincremento
	
    public abstract void createAsociacionEjercicioRutina(int ej_id,int rut_id);
    public abstract List<EjerciciosDeRutina> getAllEjerciciosDeRutina(int rut_id); //Todos los Ejercicios de una rutina
   
    public abstract List<EjerciciosDeRutina> getEjercicioDeRutina(int ej_id,int rut_id); 
    // public abstract List<EjerciciosDeRutina> getRutinasDeEjercicio(int ej_id); //Todas las Rutinas de un ejercicio
    public abstract void deleteEjercicioDeRutina(int ej_id,int rut_id);
    public abstract void deleteAllEjercicio(int rut_id);
}