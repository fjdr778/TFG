package src.rutina.app.Dao;

import java.util.List;
import src.rutina.app.Objects.*;

/*
 * Clase que representa la interfaz DAO de videos. Abstrae las operaciones
 * necesarias sobre la base de datos relacionadas con los videos a petición
 * del controlador REST de videos.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */


public abstract interface VideosDao {

	public abstract void createVideo(String videoNombre, String videoUrl,int ej_id,String userId);
	
	public abstract List<Videos> getVideo(int  ej_id,String userId);

	public abstract void deleteVideo(int ej_id,String userId);

	public abstract void updateVideo(String videoNombre, String videoUrl,int ej_id,String userId);
}
