package src.rutina.app.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import src.rutina.app.Constants.UriConstants;
import src.rutina.app.DaoImpl.RutinaDaoImpl;
import src.rutina.app.Objects.Rutina;
import src.rutina.app.Objects.Ejercicio;
import src.rutina.app.DaoImpl.EjercicioDaoImpl;
import src.rutina.app.Objects.Videos;
import src.rutina.app.DaoImpl.VideosDaoImpl;

/*
 * Clase que representa un controlador REST de Rutinas. Mapea las operaciones
 * sobre recursos REST relacionados con Rutinas y hace uso del DAO para
 * hacerlas efectivas en la base de datos.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */

@RestController
@ImportResource("classpath:spring/config/beanLocations.xml")
public class RutinaController {

    // Obtenemos el DAO mediante inyección de dependencias
    @Autowired
    private RutinaDaoImpl rutinaDao;    
    @Autowired
    private EjercicioDaoImpl ejercicioDao;
    
    @Autowired
    private VideosDaoImpl videosDao;
    
    
    // Obtiene una rutina de la base de datos
    @RequestMapping(value = UriConstants.RUTINAS, method = RequestMethod.GET)
    public @ResponseBody List<Rutina> getRutina(
    	@PathVariable("owner_id") String ownerId, 
	    @PathVariable("rut_id") int rut_id) {

    	return this.rutinaDao.getRutina(ownerId,rut_id);
    }

    // Obtiene todas las rutinas de la base de datos
    @RequestMapping(value = UriConstants.ALL_RUTINAS, method = RequestMethod.GET)
    public @ResponseBody List<Rutina> getAllRutinas(
    		@PathVariable("owner_id") String ownerId,@RequestParam("rutina_Pub_Priv") boolean rutina_Pub_Priv,
    		@RequestParam("rutina_busqueda") String rutina_busqueda) 
    {
    	System.out.println(ownerId);
    	System.out.println(rutina_Pub_Priv);
    	System.out.println(rutina_busqueda);
    	

    	return this.rutinaDao.getAllRutinas(ownerId,rutina_Pub_Priv,rutina_busqueda);
    }
    
    // Eliminar una rutina de la base de datos
    @RequestMapping(value = UriConstants.RUTINAS, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRutina(
    		@PathVariable("owner_id") String ownerId,
    		@PathVariable("rut_id") int rut_id) {

    	this.rutinaDao.deleteRutina(ownerId,rut_id);
    }

    // Eliminar todas las rutinas de la base de datos
    @RequestMapping(value = UriConstants.ALL_RUTINAS, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllRutinas(@PathVariable("owner_id") String ownerId) {
    	
    	this.rutinaDao.deleteAllRutinas(ownerId);
    }

    // Añade una rutina en la base de datos
    @RequestMapping(consumes="application/json", value = UriConstants.ALL_RUTINAS, method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRutina(@PathVariable("owner_id") String ownerId,
    		@RequestBody Rutina rutina) {
    	
    	//System.out.println("controller: "+rutina_Pub_Priv);

    	//System.out.println(rutina.getRutinaNombre()+rutina.getRutinaDescripcion()+rutina.getRutinaInfo_Rutina()+rutina.isRutinaPub_Priv());
    	this.rutinaDao.createRutina(rutina.getRutinaNombre(),rutina.getRutinaDescripcion(),
    			rutina.getRutinaInfo_Rutina(),rutina.isRutinaPub_Priv(),ownerId);
    }

   //Actualiza una rutina en la base de datos
    @RequestMapping(value = UriConstants.RUTINAS, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRutina(@PathVariable("owner_id") String ownerId,
    		@PathVariable("rut_id") int rut_id,@RequestBody Rutina rutina) {
    	
    	//System.out.println("Controlador:"+ rut_id+rutina.getRutinaNombre()+rutina.getRutinaDescripcion()+rutina.getRutinaInfo_Rutina()+rutina.isRutinaPub_Priv());
	this.rutinaDao.updateRutina(rut_id, rutina.getRutinaNombre(),rutina.getRutinaDescripcion(),rutina.getRutinaInfo_Rutina(),rutina.isRutinaPub_Priv(),ownerId);
    }
    
    
    //Actualiza una rutina en la base de datos
    @RequestMapping(value = UriConstants.RUTINAS_DOWNLOAD, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void downloadRutina(@PathVariable("owner_id") String ownerId, @PathVariable("rut_id") int rut_id) {

		// Obtengo la turina y los ejercicios asociados a esa rutina:
		List<Rutina> rutina = this.rutinaDao.getRutina(ownerId, rut_id);
		List<Ejercicio> ejercicio = this.ejercicioDao.getAllEjerciciosDeRutina(rut_id);


		/* CREACION DEL JSON */

		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		JsonObjectBuilder jsonBuilder1 = Json.createObjectBuilder();

		// create an array of key-value pairs
		JsonArrayBuilder ejer = Json.createArrayBuilder();
		JsonArrayBuilder total = Json.createArrayBuilder();

		// Para cada ejercicio, creo el Json:
		for (int i = 0; i < ejercicio.size(); i++) {

			List<Videos> video = this.videosDao.getVideo((int) ejercicio.get(i).getEj_id(), ownerId);
			jsonBuilder.add("Nombre", video.get(0).getVideoNombre());
			jsonBuilder.add("Titulo", ejercicio.get(i).getEjercicioTitulo());
			jsonBuilder.add("Subtitulo", ejercicio.get(i).getEjercicioSubtitulo());
			jsonBuilder.add("Descripcion", ejercicio.get(i).getEjercicioDescripcion());
			jsonBuilder.add("Estado de forma", ejercicio.get(i).getEjercicioEstado_Forma());
			jsonBuilder.add("Repeticiones", ejercicio.get(i).getEjercicioRepeticiones());

			// create each key-value pair as seperate object and add it to the
			// array
			ejer.add(jsonBuilder);
		}

		JsonArray ejerArr = ejer.build();
		// add contacts array object

		jsonBuilder.add("descripcion rutina", rutina.get(0).getRutinaDescripcion());
		jsonBuilder1.add("informacion ejercicios", ejerArr);

		total.add(jsonBuilder);
		total.add(jsonBuilder1);

		JsonArray totalArr = total.build();

		// here we are writing to String writer.
		// if you want you can write it to a file as well
		StringWriter strWtr = new StringWriter();
		JsonWriter jsonWtr = Json.createWriter(strWtr);
		jsonWtr.writeArray(totalArr);
		jsonWtr.close();

		System.out.println(strWtr.toString());

		

		
		FileWriter file;
		try {
			file = new FileWriter("/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/json/rutina_" + rut_id + ".json");
			file.write(strWtr.toString());
			file.close();
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* GENERACION DEL .ZIP */
		try {
			FileOutputStream fos = new FileOutputStream("/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/zip/rutina_" + rut_id + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			// Json:
			String json = "/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/json/rutina_" + rut_id + ".json";
			// addToZipFile(json, zos);

			File file1 = new File(json);

			FileInputStream fis = new FileInputStream(file1);

			ZipEntry zipEntry = new ZipEntry("rutina_" + rut_id + ".json");
			zos.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zos.write(bytes, 0, length);
			}

			// Videos:
			for (int j = 0; j < ejercicio.size(); j++) {
				List<Videos> video = this.videosDao.getVideo((int) ejercicio.get(j).getEj_id(), ownerId);

				String videos = video.get(0).getVideoUrl();

				File file2 = new File(videos);

				FileInputStream fis2 = new FileInputStream(file2);

				ZipEntry zipEntry1 = new ZipEntry(video.get(0).getVideoNombre());
				zos.putNextEntry(zipEntry1);

				byte[] bytes1 = new byte[1024];
				int length1;
				while ((length1 = fis2.read(bytes1)) >= 0) {
					zos.write(bytes1, 0, length1);
				}

			}

			zos.close();
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    
   /* @RequestMapping(value = UriConstants.RUTINAS_DOWNLOAD, method = RequestMethod.GET)
    public void getFile(
    		@PathVariable("owner_id") String ownerId, @PathVariable("rut_id") int rut_id, 
        HttpServletResponse response) throws IOException {
    	
    	
    	
            String src= "/var/rutina_app/zip/rutina_" + rut_id + ".zip";
            InputStream is = new FileInputStream(src);
            IOUtils.copy(is, response.getOutputStream());
            response.setContentType("application/zip");
            response.setHeader("Content-disposition", "attachment; filename=" + src);
            
            System.out.println("Downloading");
            

            
            response.flushBuffer();
            
            
    }*/
    
}

