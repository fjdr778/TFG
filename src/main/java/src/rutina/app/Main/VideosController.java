package src.rutina.app.Main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import src.rutina.app.DaoImpl.*;
import src.rutina.app.Dao.*;
import src.rutina.app.Constants.*;
import src.rutina.app.Objects.*;

@RestController
@ImportResource("classpath:spring/config/beanLocations.xml")
public class VideosController{

	    // Obtenemos el DAO mediante inyección de dependencias
	    @Autowired
	    private VideosDaoImpl videosDao;

	    // Obtiene un video de la base de datos
	   @RequestMapping(value = UriConstants.VIDEOS, method = RequestMethod.GET)
	    public @ResponseBody List<Videos> getVideo(
	    	@PathVariable("owner_id") String ownerId, 
		    @PathVariable("ej_id") int ej_id) {

	    	return this.videosDao.getVideo(ej_id,ownerId);
	    }

	    
	    // Eliminar un video de la base de datos
	    @RequestMapping(value = UriConstants.VIDEOS, method = RequestMethod.DELETE)
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteVideo(
	    		@PathVariable("owner_id") String ownerId,
	    		@PathVariable("ej_id") int ej_id) {

	    	this.videosDao.deleteVideo(ej_id,ownerId);
	    }



	    // Añade un video en la base de datos
	    @RequestMapping(consumes="application/json", value = UriConstants.VIDEOS, method = RequestMethod.POST)
	    //@ResponseStatus(HttpStatus.NO_CONTENT)
	    public void addVideo(@PathVariable("owner_id") String ownerId,
	    		@PathVariable("ej_id") int ej_id,
	    		@RequestBody Videos video) {
	    	//System.out.println(rutina.getRutinaNombre()+rutina.getRutinaDescripcion()+rutina.getRutinaInfo_Rutina());
	    	this.videosDao.createVideo(video.getVideoNombre(), video.getVideoUrl(),ej_id,ownerId);
	    }

	   //Actualiza una rutina en la base de datos
	    //Testear!!!
	    @RequestMapping(value = UriConstants.VIDEOS, method = RequestMethod.PUT)
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void updateVideo(@PathVariable("owner_id") String ownerId,
	    		@PathVariable("ej_id") int ej_id,@RequestBody Videos video) {

		this.videosDao.updateVideo(video.getVideoNombre(), video.getVideoUrl(),ej_id,ownerId);
	    }
	}

