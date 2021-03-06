package src.rutina.app.Main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
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
import src.rutina.app.DaoImpl.EjercicioDaoImpl;
import src.rutina.app.Objects.Ejercicio;

/*
 * Clase que representa un controlador REST de Ejercicios. Mapea las operaciones
 * sobre recursos REST relacionados con Ejercicios y hace uso del DAO para
 * hacerlas efectivas en la base de datos.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * version 2.0.0
 *
 */


@RestController
@ImportResource("classpath:spring/config/beanLocations.xml")
public class EjercicioController {

	// Obtenemos el DAO mediante inyección de dependencias
	@Autowired
	private EjercicioDaoImpl ejercicioDao;

	// Obtiene un ejercicio de la base de datos
	@RequestMapping(value = UriConstants.EJERCICIOS, method = RequestMethod.GET )
	public @ResponseBody List<Ejercicio> getEjercicio(
			@PathVariable("user_id") String userId,
			@PathVariable("ej_id") int ej_id) {

		return this.ejercicioDao.getEjercicio(userId, ej_id);
	}

	// Obtiene todos los ejercicios de la base de datos para un usuario
	@RequestMapping(value = UriConstants.ALL_EJERCICIOS, method = RequestMethod.GET)
	public @ResponseBody List<Ejercicio> getAllEjercicio(
			@PathVariable("user_id") String userId,@RequestParam("ejercicio_Pub_Priv") boolean ejercicio_Pub_Priv,
			@RequestParam("ejercicio_busqueda") String ejercicio_busqueda) {

		return this.ejercicioDao.getAllEjercicio(userId,ejercicio_Pub_Priv,ejercicio_busqueda);
	}

	// Obtiene todos los ejercicios de la base de datos asociados a una rutina
	@RequestMapping(value = UriConstants.ALL_RUTINAS_EJERCICIOS, method = RequestMethod.GET)
	public @ResponseBody List<Ejercicio> getAllEjerciciosDeRutina(
			@PathVariable("rut_id") int rut_id,@RequestParam("rutPub") String rutPub) {

		if(rutPub.equals("nopub"))
		{
			return this.ejercicioDao.getAllEjerciciosDeRutina(rut_id);
		}
		else
		{
			return this.ejercicioDao.getEjerciciosDeRutinaPublica(rut_id,rutPub);

		}
	}

	//Obtiene todos los ejercicios asociados a una rutina que no esten asociados a dicha rutina
	@RequestMapping(value = UriConstants.ALL_RUTINAS_NO_EJERCICIOS, method = RequestMethod.GET)
	public @ResponseBody List<Ejercicio> getAllEjerciciosNoDeRutina(
			@PathVariable("rut_id") int rut_id,@RequestParam("userId") String userId,
			@RequestParam("ejercicio_busqueda") String ejercicio_busqueda) {

		return this.ejercicioDao.getAllEjerciciosNoDeRutina(rut_id,userId,ejercicio_busqueda);
	}

	// Eliminar un ejercicio de la base de datos
	@RequestMapping(value = UriConstants.EJERCICIOS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEjercicio(@PathVariable("user_id") String userId,
			@PathVariable("ej_id") int ej_id) {

		this.ejercicioDao.deleteEjercicio(userId,ej_id);
	}

	// Eliminar todos los ejericios de la base de datos
	@RequestMapping(value = UriConstants.ALL_EJERCICIOS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAllEjericios(@PathVariable("user_id") String userId) {

		this.ejercicioDao.deleteAllEjercicio(userId);
	}

	//Eliminar ejercicio asociado a rutina
	@RequestMapping(value = UriConstants.RUTINAS_EJERCICIOS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEjercicioDeRutina(@PathVariable("rut_id") int rut_id,
			@PathVariable("ej_id") int ej_id) {

		this.ejercicioDao.deleteEjercicioDeRutina(rut_id,ej_id);
	}

	//Eliminar todos los ejercicios asociados a una rutina
	@RequestMapping(value = UriConstants.ALL_RUTINAS_EJERCICIOS, method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEjerciciosDeRutina(@PathVariable("rut_id") int rut_id) {

		this.ejercicioDao.deleteEjerciciosDeRutina(rut_id);
	}

	// Añade un ejerciio en la base de datos
	@RequestMapping(value = UriConstants.ALL_EJERCICIOS, method = RequestMethod.POST)
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addEjercicio(@PathVariable("user_id") String userId,
			@RequestParam("ejercicio_Pub_Priv") boolean ejercicio_Pub_Priv,
			@RequestBody Ejercicio ejercicio) {

		ejercicio.setEjercicioPub_Priv(ejercicio_Pub_Priv);

		this.ejercicioDao.createEjercicio(ejercicio.getEjercicioNombre(), ejercicio.getEjercicioTitulo(),
				ejercicio.getEjercicioSubtitulo(), ejercicio.getEjercicioDescripcion(),
				ejercicio.getEjercicioEstado_Forma(), ejercicio.getEjercicioRepeticiones(),ejercicio.isEjercicioPub_Priv(),userId);
	}

	// Actualiza un ejercicio en la base de datos
	@RequestMapping(value = UriConstants.EJERCICIOS, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEjercicio(@PathVariable("user_id") String userId,
			@PathVariable("ej_id") int ej_id, @RequestParam("ejercicio_Pub_Priv") boolean ejercicio_Pub_Priv,
			@RequestBody Ejercicio ejercicio) {

		ejercicio.setEjercicioPub_Priv(ejercicio_Pub_Priv);

		this.ejercicioDao.updateEjercicio(ej_id,ejercicio.getEjercicioNombre(), ejercicio.getEjercicioTitulo(),
				ejercicio.getEjercicioSubtitulo(), ejercicio.getEjercicioDescripcion(),
				ejercicio.getEjercicioEstado_Forma(), ejercicio.getEjercicioRepeticiones(),ejercicio.isEjercicioPub_Priv(), userId);
	}


	// Asocia un ejercicio con una rutina
	@RequestMapping(value = UriConstants.RUTINAS_EJERCICIOS, method = RequestMethod.POST)
	public void asociateEjercicio(@PathVariable("rut_id") int rut_id,@RequestParam("user_id") String userId,
			@PathVariable("ej_id") int ej_id) {

		this.ejercicioDao.AsociateEjercicioDeRutina(ej_id,rut_id,userId);
	}
}
