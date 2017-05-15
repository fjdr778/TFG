package src.rutina.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/*
 * Clase que representa un controlador REST de Ejercicios asociados a Rutinas. Mapea las operaciones
 * sobre recursos REST relacionados con Ejercicios rutinas y hace uso del DAO para
 * hacerlas efectivas en la base de datos.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */


@RestController
@ImportResource("classpath:spring/config/beanLocations.xml")
public class EjerciciosDeRutinaController {

	// Obtenemos el DAO mediante inyección de dependencias
    @Autowired
    private EjerciciosDeRutinaDaoImpl ejerciciosderutinaDao;
	
    // Obtiene todos los ejercicios de la base de datos para una rutina
    @RequestMapping(value = UriConstants.ALL_RUTINAS_EJERCICIOS, method = RequestMethod.GET)
    public @ResponseBody List<EjerciciosDeRutina> getAllEjerciciosDeRutina(
    		@PathVariable("rut_id") int rut_id) {

	return this.ejerciciosderutinaDao.getAllEjerciciosDeRutina(rut_id);
    }
    @RequestMapping(value = UriConstants.RUTINAS_EJERCICIOS, method = RequestMethod.GET) 
    // Obtiene un ejercicio de la base de datos para una rutina
    public @ResponseBody List<EjerciciosDeRutina> getEjercicioDeRutina(@PathVariable("ej_id") int ej_id,
    		@PathVariable("rut_id") int rut_id){
    	return this.ejerciciosderutinaDao.getEjercicioDeRutina( ej_id,rut_id);
    }
    
 // Añade un ejercicio a una rutina en la BBDD
    @RequestMapping(value = UriConstants.ALL_RUTINAS_EJERCICIOS, method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void addEjercicioDeRutina(
    		@RequestBody EjerciciosDeRutina ejercicioderutina) {
	this.ejerciciosderutinaDao.createAsociacionEjercicioRutina((int) ejercicioderutina.getEj_id(), 
			(int) ejercicioderutina.getRut_id());
    }
	
    
 // Elimina todos los ejercicios de la base de datos para una rutina
    @RequestMapping(value = UriConstants.ALL_RUTINAS_EJERCICIOS, method = RequestMethod.DELETE)
    public void deleteAllEjercicio(@PathVariable("rut_id") int rut_id){

    	this.ejerciciosderutinaDao.deleteAllEjercicio(rut_id);
    }
    
 // Elimina un ejercicio de la base de datos para una rutina
    @RequestMapping(value = UriConstants.RUTINAS_EJERCICIOS, method = RequestMethod.DELETE)
    public void deleteEjercicioDeRutina(@PathVariable("ej_id") int ej_id,
    		@PathVariable("rut_id") int rut_id){
    	this.ejerciciosderutinaDao.deleteEjercicioDeRutina(ej_id,rut_id);
    }
	
	
	
}
