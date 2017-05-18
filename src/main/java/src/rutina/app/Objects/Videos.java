package src.rutina.app.Objects;

public class Videos {
	
	private long ej_id;
	private String ownerId;
	private String videoNombre;
	private String videoUrl;
	
	
	
	public Videos(String videoNombre, String videoUrl,long ej_id,String ownerId) {
	this.videoNombre=videoNombre;
	this.videoUrl=videoUrl;
	this.ownerId=ownerId;
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

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

    
}
