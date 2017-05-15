package src.rutina.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * Clase que representa un controlador REST de Ejercicios. Mapea las operaciones
 * sobre recursos REST relacionados con Ejercicios y hace uso del DAO para
 * hacerlas efectivas en la base de datos.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
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
	    @PathVariable("owner_id") String ownerId,
	    @PathVariable("ej_id") int ej_id) {

	return this.ejercicioDao.getEjercicio(ownerId, ej_id);
    }

    // Obtiene todos los ejercicios de la base de datos para un usuario
    @RequestMapping(value = UriConstants.ALL_EJERCICIOS, method = RequestMethod.GET)
    public @ResponseBody List<Ejercicio> getAllEjercicio(
	    @PathVariable("owner_id") String ownerId) {

	return this.ejercicioDao.getAllEjercicio(ownerId);
    }

    // Eliminar un ejericio de la base de datos
    @RequestMapping(value = UriConstants.EJERCICIOS, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("owner_id") String ownerId,
	    @PathVariable("ej_id") int ej_id) {

	this.ejercicioDao.deleteEjercicio(ownerId,ej_id);
    }

    // Eliminar todos los ejericios de la base de datos
    @RequestMapping(value = UriConstants.ALL_EJERCICIOS, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllEvents(@PathVariable("owner_id") String ownerId) {

	this.ejercicioDao.deleteAllEjercicio(ownerId);
    }

    // Añade un ejercicio en la base de datos
    @RequestMapping(value = UriConstants.ALL_EJERCICIOS, method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEvent(@PathVariable("owner_id") String ownerId, @RequestBody Ejercicio ejercicio) {

	this.ejercicioDao.createEjercicio(ejercicio.getEjercicioNombre(), ejercicio.getEjercicioTitulo(),
			ejercicio.getEjercicioSubtitulo(), ejercicio.getEjercicioDescripcion(),
			ejercicio.getEjercicioEstado_Forma(), ejercicio.getEjercicioRepeticiones(),
			ejercicio.getEjercicioRep_Video(), ownerId);
    }

    // Actualiza un ejercicio en la base de datos
    @RequestMapping(value = UriConstants.EJERCICIOS, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEvent(@PathVariable("owner_id") String ownerId,
	    @PathVariable("ej_id") int ej_id, @RequestBody Ejercicio ejercicio) {

	this.ejercicioDao.updateEjercicio(ej_id,ejercicio.getEjercicioNombre(), ejercicio.getEjercicioTitulo(),
			ejercicio.getEjercicioSubtitulo(), ejercicio.getEjercicioDescripcion(),
			ejercicio.getEjercicioEstado_Forma(), ejercicio.getEjercicioRepeticiones(),
			ejercicio.getEjercicioRep_Video(), ownerId);
    }
}
