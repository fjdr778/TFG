package src.rutina.app.Objects;

public class EjerciciosDeRutina {
	private int ej_id;
	private int rut_id;
	
	public EjerciciosDeRutina(int rut_id,int ej_id){		
		this.rut_id=rut_id;
		this.ej_id=ej_id;
		
	}
	
	public EjerciciosDeRutina()
	{}
	
	public long getEj_id() {
		return ej_id;
	}
	public void setEj_id(int ej_id) {
		this.ej_id = ej_id;
	}
	public long getRut_id() {
		return rut_id;
	}
	public void setRut_id(int rut_id) {
		this.rut_id = rut_id;
	}
}
