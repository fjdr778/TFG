package src.rutina.app.Objects;

/*
 * Clase que representa un Vvideo en la aplicación.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 */


public class Videos {

	private long ej_id;
	private String userId;
	private String videoNombre;
	private String videoUrl;

	public Videos(String videoNombre, String videoUrl,long ej_id,String userId) {
		this.videoNombre=videoNombre;
		this.videoUrl=videoUrl;
		this.userId=userId;
		this.ej_id=ej_id;

	}

	public Videos(){
	}


	public long getEj_Id() {
		return ej_id;
	}

	public void setEj_Id(long ej_id) {
		this.ej_id = ej_id;
	}

	public String getVideoNombre() {
		return videoNombre;
	}

	public void setVideoNombre(String videoNombre) {
		this.videoNombre = videoNombre;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
