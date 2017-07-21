package src.rutina.app.Objects;

/*
 * Clase que representa una rutina en la aplicación.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 */

public class Rutina {
	private long rut_id;
	private String userId;
	private String rutinaNombre;
	private String rutinaDescripcion;
	private String rutinaInfo_Rutina;
	private boolean rutinaPub_Priv;


	public Rutina(long rut_id,String rutinaNombre,String rutinaDescripcion, String rutinaInfo_Rutina,boolean rutinaPub_Priv,String userId) {
		this.userId=userId;
		this.rut_id=rut_id;
		this.rutinaNombre=rutinaNombre;
		this.rutinaDescripcion=rutinaDescripcion;
		this.rutinaInfo_Rutina=rutinaInfo_Rutina;	
		this.rutinaPub_Priv=rutinaPub_Priv;
	}

	public Rutina(){
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getRut_id() {

		return rut_id;
	}

	public void setRut_id(long rut_id) {
		this.rut_id = rut_id;
	}

	public String getRutinaNombre(){
		return rutinaNombre;
	}

	public void setRutinaNombre(String rutinaNombre){
		this.rutinaNombre=rutinaNombre;
	}

	public String getRutinaDescripcion(){
		return rutinaDescripcion;
	}

	public void setRutinaDescripcion(String rutinaDescripcion){
		this.rutinaDescripcion=rutinaDescripcion;
	}

	public String getRutinaInfo_Rutina(){

		return rutinaInfo_Rutina;
	}

	public void setRutinaInfo_Rutina(String rutinaInfo_Rutina){
		this.rutinaInfo_Rutina=rutinaInfo_Rutina;
	}

	public boolean isRutinaPub_Priv() {
		return this.rutinaPub_Priv;
	}

	public void setRutinaPub_Priv(boolean rutinaPub_Priv) {
		System.out.println(rutinaPub_Priv);
		this.rutinaPub_Priv = rutinaPub_Priv;
	}


}
