package src.rutina.app.Main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import src.rutina.app.Constants.UriConstants;
import src.rutina.app.DaoImpl.RutinaDaoImpl;
import src.rutina.app.Objects.Rutina;

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

    // Obtiene una rutina de la base de datos
    @RequestMapping(value = UriConstants.RUTINAS, method = RequestMethod.GET)
    public @ResponseBody List<Rutina> getEvent(
    	@PathVariable("owner_id") String ownerId, 
	    @PathVariable("rut_id") int rut_id) {

    	return this.rutinaDao.getRutina(ownerId,rut_id);
    }

    // Obtiene todas las rutinas de la base de datos
    @RequestMapping(value = UriConstants.ALL_RUTINAS, method = RequestMethod.GET)
    public @ResponseBody List<Rutina> getAllRutinas(
    		@PathVariable("owner_id") String ownerId) 
    {
    	return this.rutinaDao.getAllRutinas(ownerId);
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
    	//System.out.println(rutina.getRutinaNombre()+rutina.getRutinaDescripcion()+rutina.getRutinaInfo_Rutina());
    	this.rutinaDao.createRutina(rutina.getRutinaNombre(),rutina.getRutinaDescripcion(),rutina.getRutinaInfo_Rutina(),rutina.isRutinaPub_Priv(),ownerId);
    }

   //Actualiza una rutina en la base de datos
    @RequestMapping(value = UriConstants.RUTINAS, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRutina(@PathVariable("owner_id") String ownerId,
    		@PathVariable("rut_id") int rut_id,@RequestBody Rutina rutina) {

	this.rutinaDao.updateRutina(rut_id, rutina.getRutinaNombre(),rutina.getRutinaDescripcion(),rutina.getRutinaInfo_Rutina(),rutina.isRutinaPub_Priv(),ownerId);
    }
}

