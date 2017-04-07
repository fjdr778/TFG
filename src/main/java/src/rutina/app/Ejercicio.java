package src.rutina.app;


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
	private long ej_id, rut_id;
	private String Nombre;
    private String Titulo;
    private String Subtitulo;
    private String Descripcion;
    private String Estado_Forma;
    private int Repeticiones;
    private int Rep_Video;

    

	public Ejercicio(long ej_id, String Nombre, String Titulo, String Subtitulo,String Descripcion,
    		String Estado_Forma,int Repeticiones,int Rep_Video) {
	this.ej_id=ej_id;
	this.Nombre=Nombre;
	this.Titulo=Titulo;
	this.Subtitulo=Subtitulo;
	this.Descripcion=Descripcion;
	this.Estado_Forma=Estado_Forma;
	this.Repeticiones=Repeticiones;
	this.Rep_Video=Rep_Video;
    }
    
    public Ejercicio(){
    }
    
    public long getId() {
		return ej_id;
	}

	public void setId(long ej_id) {
		this.ej_id = ej_id;
	}
    
    public String getNombre(){
    	return Nombre;
    }
    
    public void setNombre(String Nombre){
    	this.Nombre=Nombre;
    }
    
    public String getTitulo(){	
    	return Titulo;
    }
    
    public void setTitulo(String Titulo){	
    	this.Titulo=Titulo;
    }
    
    public String getSubtitulo(){
    	return Subtitulo;
    }
    
    public void setSubtitulo(String Subtitulo){	
    	this.Subtitulo=Subtitulo;
    }
    
    public String getDescripcion(){
    	return Descripcion;
    }
    
    public void setDescripcion(String Descripcion){	
    	this.Descripcion=Descripcion;
    }
    
    public String getEstado_Forma(){
    	return Estado_Forma;
    }
    
    public void setEstado_Forma(String Estado_Forma){	
    	this.Estado_Forma=Estado_Forma;
    }
    
    public int getRepeticiones(){
    	return Repeticiones;
    }
    
    public void setRepeticiones(int Repeticiones){	
    	this.Repeticiones=Repeticiones;
    }
    
    public int getRep_Video(){
    	return Rep_Video;
    }
    
    public void setRep_Video(int Rep_Video){	
    	this.Rep_Video=Rep_Video;
    }

	public long getrut_id() {
		return rut_id;
	}

	public void setrut_id(long rut_id) {
		this.rut_id = rut_id;
	}
}

















	

