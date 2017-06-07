package src.rutina.app.Objects;

public class Rutina {
	private long rut_id;
	private String ownerId;
	private String rutinaNombre;
	private String rutinaDescripcion;
	private String rutinaInfo_Rutina;
	private boolean rutinaPub_Priv;
	
	
	public Rutina(long rut_id,String rutinaNombre,String rutinaDescripcion, String rutinaInfo_Rutina,boolean rutinaPub_Priv,String ownerId) {
		this.ownerId=ownerId;
		this.rut_id=rut_id;
		this.rutinaNombre=rutinaNombre;
		this.rutinaDescripcion=rutinaDescripcion;
		this.rutinaInfo_Rutina=rutinaInfo_Rutina;	
		this.rutinaPub_Priv=rutinaPub_Priv;
	}
	
	public Rutina(){
	}
	
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
