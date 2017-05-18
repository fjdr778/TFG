package src.rutina.app.Dao;

import java.util.List;
import src.rutina.app.Objects.*;




public abstract interface VideosDao {
	
	 public abstract void createVideo(String videoNombre, String videoUrl,int ej_id,String ownerId);
	 public abstract List<Videos> getVideo(int  ej_id,String ownerId);

	 public abstract void deleteVideo(int ej_id,String ownerId);

	 public abstract void updateVideo(String videoNombre, String videoUrl,int ej_id,String ownerId);
}
