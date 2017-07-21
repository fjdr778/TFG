package src.rutina.app.Dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import src.rutina.app.Objects.Ejercicio;

import java.sql.Time;
import java.sql.Date;

/*
 * Clase que representa la interfaz DAO de ejercicios. Abstrae las operaciones
 * necesarias sobre la base de datos relacionadas con los ejercicios a petición
 * del controlador REST de ejercicios.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

public abstract interface EjercicioDao {

	// No se le pasa el identificador de ejercicio para crearlo porque en la base 
	// de datos es un campo con autoincremento

	public abstract void createEjercicio(String ejercicioNombre, String ejercicioTitulo,
			String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
			int ejercicioRepeticiones, boolean ejercicio_Pub_Priv, String userId);

	public abstract void updateEjercicio(int ej_id, String ejercicioNombre, String ejercicioTitulo,
			String ejercicioSubtitulo,String ejercicioDescripcion, String ejercicioEstado_Forma, 
			int ejercicioRepeticiones,boolean ejercicio_Pub_Priv, String userId);

	public abstract void AsociateEjercicioDeRutina(int ej_id,int rut_id,String userId);

	public abstract List<Ejercicio> getEjercicio(String userId, int ej_id);
	public abstract List<Ejercicio> getAllEjercicio(String userId, boolean ejercicioPub_Priv, String ejercicio_busqueda);

	public abstract List<Ejercicio> getAllEjerciciosDeRutina(int rut_id);
	public abstract List<Ejercicio> getEjerciciosDeRutinaPublica(int rut_id, String rutPub);

	public abstract List<Ejercicio> getAllEjerciciosNoDeRutina(int rut_id,String userId,String ejercicio_busqueda);


	public abstract void deleteEjercicio(String userId, int ej_id);
	public abstract void deleteAllEjercicio(String userId);

	public abstract void deleteEjercicioDeRutina(int rut_id,int ej_id);
	public abstract void deleteEjerciciosDeRutina(int rut_id);
}