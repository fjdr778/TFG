package src.rutina.app.Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
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
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * version 2.0.0
 */

@RestController
@ImportResource("classpath:spring/config/beanLocations.xml")
public class RutinaController {

	// Obtenemos los DAO necesarios mediante inyección de dependencias
	@Autowired
	private RutinaDaoImpl rutinaDao;

	@Autowired
	private EjercicioDaoImpl ejercicioDao;

	@Autowired
	private VideosDaoImpl videosDao;

	public static ZipOutputStream zos;


	// Añade una rutina en la base de datos
	@RequestMapping(consumes="application/json", value = UriConstants.ALL_RUTINAS, method = RequestMethod.POST)
	public void addRutina(@PathVariable("user_id") String userId,
			@RequestBody Rutina rutina) {    	
		this.rutinaDao.createRutina(rutina.getRutinaNombre(),rutina.getRutinaDescripcion(),
				rutina.getRutinaInfo_Rutina(),rutina.isRutinaPub_Priv(),userId);
	}

	//Actualiza una rutina en la base de datos
	@RequestMapping(value = UriConstants.RUTINAS, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateRutina(@PathVariable("user_id") String userId,
			@PathVariable("rut_id") int rut_id,@RequestBody Rutina rutina) {
		this.rutinaDao.updateRutina(rut_id, rutina.getRutinaNombre(),rutina.getRutinaDescripcion(),rutina.getRutinaInfo_Rutina(),rutina.isRutinaPub_Priv(),userId);
	}

	// Obtiene una rutina de la base de datos, de un usuario determinado
	@RequestMapping(value = UriConstants.RUTINAS, method = RequestMethod.GET)
	public @ResponseBody List<Rutina> getRutina(
			@PathVariable("user_id") String userId, 
			@PathVariable("rut_id") int rut_id) {

		return this.rutinaDao.getRutina(userId,rut_id);
	}

	// Obtiene una rutina de la base de datos, solo por su identificador
	@RequestMapping(value = UriConstants.RUTINAS1, method = RequestMethod.GET)
	public @ResponseBody List<Rutina> getRutina1( 
			@PathVariable("rut_id") int rut_id) {

		return this.rutinaDao.getRutina1(rut_id);
	}



	// Obtiene todas las rutinas de la base de datos
	@RequestMapping(value = UriConstants.ALL_RUTINAS, method = RequestMethod.GET)
	public @ResponseBody List<Rutina> getAllRutinas(
			@PathVariable("user_id") String userId,@RequestParam("rutina_Pub_Priv") boolean rutina_Pub_Priv,
			@RequestParam("rutina_busqueda") String rutina_busqueda) 
	{ 	
		return this.rutinaDao.getAllRutinas(userId,rutina_Pub_Priv,rutina_busqueda);
	}

	// Eliminar una rutina de la base de datos
	@RequestMapping(value = UriConstants.RUTINAS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRutina(
			@PathVariable("user_id") String userId,
			@PathVariable("rut_id") int rut_id) {

		this.ejercicioDao.deleteEjerciciosDeRutina(rut_id);
		this.rutinaDao.deleteRutina(userId,rut_id);

	}

	// Eliminar todas las rutinas de la base de datos
	@RequestMapping(value = UriConstants.ALL_RUTINAS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllRutinas(@PathVariable("user_id") String userId) {

		this.rutinaDao.deleteAllRutinas(userId);
	}

	//Prepara una rutina para su descarga. Crea el paquete de la rutina (.Zip)
	@RequestMapping(value = UriConstants.RUTINAS_DOWNLOAD, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void downloadRutina(@PathVariable("user_id") String userId, @PathVariable("rut_id") int rut_id,
			@RequestParam("rutPub") String rutPub) {


		if(rutPub.equals("nopub"))
		{
				System.out.println("entra");
			// Obtengo la turina y los ejercicios asociados a esa rutina:
			List<Rutina> rutina = this.rutinaDao.getRutina(userId, rut_id);
			List<Ejercicio> ejercicio = this.ejercicioDao.getAllEjerciciosDeRutina(rut_id);
			//List<Ejercicio> ejerciciospublicos = this.ejercicioDao.getEjerciciosDeRutinaPublica(rut_id,userId);
			/* CREACION DEL JSON */

			JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
			JsonObjectBuilder jsonBuilder1 = Json.createObjectBuilder();

			// create an array of key-value pairs
			JsonArrayBuilder ejer = Json.createArrayBuilder();
			JsonArrayBuilder total = Json.createArrayBuilder();



			/*List<Videos> video = this.videosDao.getVideo((int) ejerciciospublicos.get(0).getEj_id(), userId);
        		System.out.print(ejercicio.get(0).getEj_id());
        		System.out.print(video.get(0).getVideoNombre());*/
			// Para cada ejercicio, creo el Json:
			for (int i = 0; i < ejercicio.size(); i++) {

				List<Videos> video = this.videosDao.getVideo((int) ejercicio.get(i).getEj_id(), ejercicio.get(i).getUserId());
				
				if(video.isEmpty()){
					
				}
				else
				{
				
				jsonBuilder.add("Nombre", video.get(0).getVideoNombre());
				jsonBuilder.add("Titulo", ejercicio.get(i).getEjercicioTitulo());
				jsonBuilder.add("Subtitulo", ejercicio.get(i).getEjercicioSubtitulo());
				jsonBuilder.add("Descripcion", ejercicio.get(i).getEjercicioDescripcion());
				jsonBuilder.add("Estado de forma", ejercicio.get(i).getEjercicioEstado_Forma());
				jsonBuilder.add("Repeticiones", ejercicio.get(i).getEjercicioRepeticiones());

				System.out.println("Id: "+ejercicio.get(i).getEj_id());
				System.out.println("Titulo: "+ ejercicio.get(i).getEjercicioTitulo());
				System.out.println("Subtitulo: "+ ejercicio.get(i).getEjercicioSubtitulo());
				System.out.println("Descripcion: "+ ejercicio.get(i).getEjercicioDescripcion());
				System.out.println("Estado de forma: "+ ejercicio.get(i).getEjercicioEstado_Forma());
				System.out.println("Repeticiones: "+ ejercicio.get(i).getEjercicioRepeticiones());
				System.out.println("Pub_priv: "+ ejercicio.get(i).isEjercicioPub_Priv());
				System.out.println("Propietario: "+ ejercicio.get(i).getUserId());
				System.out.println("Nombre: "+ video.get(0).getVideoNombre());
				// create each key-value pair as seperate object and add it to the
				// array
				ejer.add(jsonBuilder);
				}
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

				//CREO EL DIRECTORIO CORRESPONDIENTE A LA RUTINA.
				File directorio = new File("rutina_" + rut_id);	
				directorio.mkdir();	

				//COPIO EL JSON.
				Path FROM = Paths.get("/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/json/rutina_" + rut_id + ".json");
				Path TO = Paths.get("rutina_" + rut_id +"/rutina_" + rut_id + ".json");
				//sobreescribir el fichero de destino, si existe, y copiar
				// los atributos, incluyendo los permisos rwx
				CopyOption[] options = new CopyOption[]{
						StandardCopyOption.REPLACE_EXISTING,
						StandardCopyOption.COPY_ATTRIBUTES
				}; 
				Files.copy(FROM, TO, options);

				//COPIO LOS VIDEOS:
				for (int j = 0; j < ejercicio.size(); j++) {
					System.out.println(ejercicio.get(j).getEj_id()+ ejercicio.get(j).getUserId());
					List<Videos> video = this.videosDao.getVideo((int) ejercicio.get(j).getEj_id(), ejercicio.get(j).getUserId());
					
					if(video.isEmpty()){
						
					}
					else
					{
					
					System.out.println(video.get(0).getUserId() + "_" + video.get(0).getEj_Id() + ".mp4");
					Path FROM1 = Paths.get("/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/uploads/" + video.get(0).getUserId() + "_" + video.get(0).getEj_Id() + ".mp4");
					Path TO1 = Paths.get("rutina_" + rut_id + "/" + video.get(0).getUserId() + "_" + video.get(0).getEj_Id() + ".mp4");
					//sobreescribir el fichero de destino, si existe, y copiar
					// los atributos, incluyendo los permisos rwx
					CopyOption[] options1 = new CopyOption[]{
							StandardCopyOption.REPLACE_EXISTING,
							StandardCopyOption.COPY_ATTRIBUTES
					}; 
					Files.copy(FROM1, TO1, options1);
					}

				}



				//Genero el .ZIP
				String filename = "rutina_" + rut_id;

				File file1 = new File (filename);

				FileOutputStream out = new FileOutputStream(file1 + ".zip");
				zos = new ZipOutputStream(out);

				recurseFiles(file1);

				zos.close();


				//Copio el .Zip al directorio zip
				File file2 = new File ("rutina_" + rut_id + ".zip");
				File file3 = new File ("/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/zip/" + "rutina_" + rut_id + ".zip");

				file2.renameTo(file3);

				File file4 = new File (filename);

				//Elimino lo creado del directorio "sts-3.8.3.RELEASE"
				file2.delete();
				FileUtils.deleteDirectory(file4);   			

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			// Obtengo la turina y los ejercicios asociados a esa rutina:
			List<Rutina> rutina = this.rutinaDao.getRutina1(rut_id);
			List<Ejercicio> ejercicio = this.ejercicioDao.getEjerciciosDeRutinaPublica(rut_id,rutPub);
			//List<Ejercicio> ejerciciospublicos = this.ejercicioDao.getEjerciciosDeRutinaPublica(rut_id,userId);
			/* CREACION DEL JSON */

			JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
			JsonObjectBuilder jsonBuilder1 = Json.createObjectBuilder();

			// create an array of key-value pairs
			JsonArrayBuilder ejer = Json.createArrayBuilder();
			JsonArrayBuilder total = Json.createArrayBuilder();



			/*List<Videos> video = this.videosDao.getVideo((int) ejerciciospublicos.get(0).getEj_id(), userId);
            		System.out.print(ejercicio.get(0).getEj_id());
            		System.out.print(video.get(0).getVideoNombre());*/
			// Para cada ejercicio, creo el Json:
			for (int i = 0; i < ejercicio.size(); i++) {

				List<Videos> video = this.videosDao.getVideo((int) ejercicio.get(i).getEj_id(), ejercicio.get(i).getUserId());
				if(video.isEmpty()){
					
				}
				else
				{
				jsonBuilder.add("Nombre", ejercicio.get(i).getUserId() + "_" + ejercicio.get(i).getEj_id());
				jsonBuilder.add("Titulo", ejercicio.get(i).getEjercicioTitulo());
				jsonBuilder.add("Subtitulo", ejercicio.get(i).getEjercicioSubtitulo());
				jsonBuilder.add("Descripcion", ejercicio.get(i).getEjercicioDescripcion());
				jsonBuilder.add("Estado de forma", ejercicio.get(i).getEjercicioEstado_Forma());
				jsonBuilder.add("Repeticiones", ejercicio.get(i).getEjercicioRepeticiones());

				System.out.println("Id: "+ejercicio.get(i).getEj_id());
				System.out.println("Titulo: "+ ejercicio.get(i).getEjercicioTitulo());
				System.out.println("Subtitulo: "+ ejercicio.get(i).getEjercicioSubtitulo());
				System.out.println("Descripcion: "+ ejercicio.get(i).getEjercicioDescripcion());
				System.out.println("Estado de forma: "+ ejercicio.get(i).getEjercicioEstado_Forma());
				System.out.println("Repeticiones: "+ ejercicio.get(i).getEjercicioRepeticiones());
				System.out.println("Pub_priv: "+ ejercicio.get(i).isEjercicioPub_Priv());
				System.out.println("Propietario: "+ ejercicio.get(i).getUserId());
				System.out.println("Nombre: " + ejercicio.get(i).getUserId() + "_" + ejercicio.get(i).getEj_id());
				// create each key-value pair as seperate object and add it to the
				// array
				ejer.add(jsonBuilder);
				}
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

				//CREO EL DIRECTORIO CORRESPONDIENTE A LA RUTINA.
				File directorio = new File("rutina_" + rut_id);	
				directorio.mkdir();	

				//COPIO EL JSON.
				Path FROM = Paths.get("/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/json/rutina_" + rut_id + ".json");
				Path TO = Paths.get("rutina_" + rut_id +"/rutina_" + rut_id + ".json");
				//sobreescribir el fichero de destino, si existe, y copiar
				// los atributos, incluyendo los permisos rwx
				CopyOption[] options = new CopyOption[]{
						StandardCopyOption.REPLACE_EXISTING,
						StandardCopyOption.COPY_ATTRIBUTES
				}; 
				Files.copy(FROM, TO, options);

				//COPIO LOS VIDEOS:
				for (int j = 0; j < ejercicio.size(); j++) {
					List<Videos> video = this.videosDao.getVideo((int) ejercicio.get(j).getEj_id(), ejercicio.get(j).getUserId());

					if(video.isEmpty()){
						
					}
					else
					{
					Path FROM1 = Paths.get("/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/uploads/" + video.get(0).getUserId() + "_" + video.get(0).getEj_Id() + ".mp4");
					Path TO1 = Paths.get("rutina_" + rut_id + "/" + video.get(0).getUserId() + "_" + video.get(0).getEj_Id() + ".mp4");
					//sobreescribir el fichero de destino, si existe, y copiar
					// los atributos, incluyendo los permisos rwx
					CopyOption[] options1 = new CopyOption[]{
							StandardCopyOption.REPLACE_EXISTING,
							StandardCopyOption.COPY_ATTRIBUTES
					}; 
					Files.copy(FROM1, TO1, options);
					}
				}



				//Genero el .ZIP
				String filename = "rutina_" + rut_id;

				File file1 = new File (filename);

				FileOutputStream out = new FileOutputStream(file1 + ".zip");
				zos = new ZipOutputStream(out);

				recurseFiles(file1);

				zos.close();


				//Copio el .Zip al directorio zip
				File file2 = new File ("rutina_" + rut_id + ".zip");
				File file3 = new File ("/home/fran/sts-bundle/pivotal-tc-server-developer-3.2.2.RELEASE/Servidor1/webapps/ROOT/rutina_app/zip/" + "rutina_" + rut_id + ".zip");
				
				file2.renameTo(file3);

				File file4 = new File ("rutina_" + rut_id);
				FileUtils.deleteDirectory(file4); 
				//Elimino lo creado del directorio "sts-3.8.3.RELEASE"
				file2.delete();
				//file4.delete();   			

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	
	
	//Metodo para crear el .Zip con los videos y ejercicios en un directorio.
	public static void recurseFiles(File file1) throws IOException{

		if(file1.isDirectory()){

			String [] fileNames = file1.list();
			if(fileNames !=null)
			{
				for(int i=0; i<fileNames.length;i++)
				{
					recurseFiles(new File (file1,fileNames[i]));

				}		
			}
		}
		else
		{		  
			byte[] buf = new byte[1024];
			int len;
			ZipEntry zipEntry = new ZipEntry(file1.toString());

			FileInputStream fin = new FileInputStream(file1);
			BufferedInputStream in = new BufferedInputStream(fin);
			zos.putNextEntry(zipEntry);

			while ((len = in.read(buf)) >= 0) {
				zos.write(buf, 0, len);
			}

			in.close();
			zos.closeEntry();

		}

	}



}

