package src.rutina.app.Main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import src.rutina.app.DaoImpl.*;
import src.rutina.app.Dao.*;
import src.rutina.app.Constants.*;
import src.rutina.app.Objects.*;

@RestController
@MultipartConfig(maxFileSize = 1024*1024*1024, maxRequestSize = 1024*1024*1024)
@ImportResource("classpath:spring/config/beanLocations.xml")
public class VideosController{

	    // Obtenemos el DAO mediante inyección de dependencias
	    @Autowired
	    private VideosDaoImpl videosDao;

	    // The Environment object will be used to read parameters from the 
	    // application.properties configuration file
	    @Autowired
	    private Environment env;
	    
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
	    	
	    	try{

	    		
	    		File file = new File("/var/rutina_app/uploads/" + ownerId + "_" + ej_id +  ".mp4");

	    		if(file.delete()){
	    			System.out.println(file.getName() + " is deleted!");
	    	    	this.videosDao.deleteVideo(ej_id,ownerId);
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}

	    	}catch(Exception e){

	    		e.printStackTrace();

	    	}
	    }



	    // Añade un video en la base de datos
	   /* @RequestMapping(consumes="application/json", value = UriConstants.VIDEOS, method = RequestMethod.POST)
	    //@ResponseStatus(HttpStatus.NO_CONTENT)
	    public void addVideo(@PathVariable("owner_id") String ownerId,
	    		@PathVariable("ej_id") int ej_id,
	    		@RequestBody Videos video) {
	    	//System.out.println(rutina.getRutinaNombre()+rutina.getRutinaDescripcion()+rutina.getRutinaInfo_Rutina());
	    	this.videosDao.createVideo(video.getVideoNombre(), video.getVideoUrl(),ej_id,ownerId);
	    }
*/   
	        
	    /**
	     * POST /uploadFile -> receive and locally save a file.
	     * 
	     * @param uploadfile The uploaded file as Multipart file parameter in the 
	     * HTTP request. The RequestParam name must be the same of the attribute 
	     * "name" in the input tag with type file.
	     * 
	     * @return An http OK status in case of success, an http 4xx status in case 
	     * of errors.
	     */
	    @RequestMapping(value = UriConstants.VIDEOS, method = RequestMethod.POST)
	    @ResponseBody
	    public void uploadFile(@PathVariable("owner_id") String ownerId,
	    		@PathVariable("ej_id") int ej_id,
	    		@RequestParam("uploadfile") MultipartFile uploadfile) {
	      

	      try {

	    	  
	        //Esto serviria para obtener el nombre original del fichero a subir
	    	  String realfilename = uploadfile.getOriginalFilename();
	    	  
	        
	        //Modificar el nombre del fichero compuesto por el nombre de usuario y un id unico del video
	        //como id unico puedo usar el id del video, ya que sera clave primaria y unica en la BBDD
	    	String filename= ownerId + "_" + ej_id+".mp4"; 
	    	
	    	// Obtengo el video a subir y su ruta de subida (/var/rutina_app/uploads)
	        String directory = env.getProperty("rutina_app.paths.uploadedFiles");
	        String filepath = Paths.get(directory, filename).toString();
	        
	        // Save the file locally
	        BufferedOutputStream stream =
	            new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	        stream.write(uploadfile.getBytes());
	        stream.close();
	        
	        
	       Videos video=new Videos();
	        video.setEj_Id(ej_id);
	        video.setVideoNombre(realfilename);
	        video.setVideoUrl(filepath);
	        video.setOwnerId(ownerId);
	        this.videosDao.createVideo(realfilename, filepath,ej_id,ownerId);
	      }
	      catch (Exception e) {
	        System.out.println(e.getMessage());
	      }
	      
	    } // method uploadFile
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	   //Actualiza una rutina en la base de datos
	    //Testear!!!
	    @RequestMapping(value = UriConstants.VIDEOS, method = RequestMethod.PUT)
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void updateVideo(@PathVariable("owner_id") String ownerId,
	    		@PathVariable("ej_id") int ej_id,@RequestBody Videos video) {

		this.videosDao.updateVideo(video.getVideoNombre(), video.getVideoUrl(),ej_id,ownerId);
	    }
	}

