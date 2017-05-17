package src.rutina.app.Objects;


/*
 * Clase que representa a un ejercicio de la aplicacion.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 * 
 *
 */

public class Ejercicio {
	private long ej_id;
	private String ownerId;
	private String ejercicioNombre;
    private String ejercicioTitulo;
    private String ejercicioSubtitulo;
    private String ejercicioDescripcion;
    private String ejercicioEstado_Forma;
    private int ejercicioRepeticiones;
    private int ejercicioRep_Video;

    

	public Ejercicio(long ej_id, String ejercicioNombre, String ejercicioTitulo, String ejercicioSubtitulo,String ejercicioDescripcion,
    		String ejercicioEstado_Forma,int ejercicioRepeticiones,int ejercicioRep_Video,String ownerId) {
	this.ej_id=ej_id;
	this.ejercicioNombre=ejercicioNombre;
	this.ejercicioTitulo=ejercicioTitulo;
	this.ejercicioSubtitulo=ejercicioSubtitulo;
	this.ejercicioDescripcion=ejercicioDescripcion;
	this.ejercicioEstado_Forma=ejercicioEstado_Forma;
	this.ejercicioRepeticiones=ejercicioRepeticiones;
	this.ejercicioRep_Video=ejercicioRep_Video;
	this.ownerId=ownerId;
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
    
    public int getEjercicioRep_Video(){
    	return ejercicioRep_Video;
    }
    
    public void setEjercicioRep_Video(int ejercicioRep_Video){	
    	this.ejercicioRep_Video=ejercicioRep_Video;
    }

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
}

















	

