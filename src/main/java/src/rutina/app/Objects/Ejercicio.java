package src.rutina.app.Objects;


/*
 * Clase que representa a un ejercicio de la aplicacion.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 * 
 *
 */

public class Ejercicio {
	private long ej_id;
	private String userId;
	private String ejercicioNombre;
	private String ejercicioTitulo;
	private String ejercicioSubtitulo;
	private String ejercicioDescripcion;
	private String ejercicioEstado_Forma;
	private int ejercicioRepeticiones;
	private boolean ejercicioPub_Priv;

	public Ejercicio(long ej_id, String ejercicioNombre, String ejercicioTitulo, String ejercicioSubtitulo,String ejercicioDescripcion,
			String ejercicioEstado_Forma,int ejercicioRepeticiones,boolean ejercicioPub_Priv, String userId) {
		this.ej_id=ej_id;
		this.ejercicioNombre=ejercicioNombre;
		this.ejercicioTitulo=ejercicioTitulo;
		this.ejercicioSubtitulo=ejercicioSubtitulo;
		this.ejercicioDescripcion=ejercicioDescripcion;
		this.ejercicioEstado_Forma=ejercicioEstado_Forma;
		this.ejercicioRepeticiones=ejercicioRepeticiones;
		this.ejercicioPub_Priv=ejercicioPub_Priv;
		this.userId=userId;
	}

	public Ejercicio(){
	}

	public long getEj_id() {
		return ej_id;
	}

	public void setEj_id(long ej_id) {
		this.ej_id = ej_id;
	}

	public String getEjercicioNombre(){
		return ejercicioNombre;
	}

	public void setEjercicioNombre(String ejercicioNombre){
		this.ejercicioNombre=ejercicioNombre;
	}

	public String getEjercicioTitulo(){	
		return ejercicioTitulo;
	}

	public void setEjercicioTitulo(String ejercicioTitulo){	
		this.ejercicioTitulo=ejercicioTitulo;
	}

	public String getEjercicioSubtitulo(){
		return ejercicioSubtitulo;
	}

	public void setEjercicioSubtitulo(String ejercicioSubtitulo){	
		this.ejercicioSubtitulo=ejercicioSubtitulo;
	}

	public String getEjercicioDescripcion(){
		return ejercicioDescripcion;
	}

	public void setEjercicioDescripcion(String ejercicioDescripcion){	
		this.ejercicioDescripcion=ejercicioDescripcion;
	}

	public String getEjercicioEstado_Forma(){
		return ejercicioEstado_Forma;
	}

	public void setEjercicioEstado_Forma(String ejercicioEstado_Forma){	
		this.ejercicioEstado_Forma=ejercicioEstado_Forma;
	}

	public int getEjercicioRepeticiones(){
		return ejercicioRepeticiones;
	}

	public void setEjercicioRepeticiones(int ejercicioRepeticiones){	
		this.ejercicioRepeticiones=ejercicioRepeticiones;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isEjercicioPub_Priv() {
		return ejercicioPub_Priv;
	}

	public void setEjercicioPub_Priv(boolean ejercicioPub_Priv) {
		this.ejercicioPub_Priv = ejercicioPub_Priv;
	}
}



















