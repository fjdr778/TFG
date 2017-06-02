package src.rutina.app.Main;

import java.sql.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import src.rutina.app.Constants.UriConstants;
import src.rutina.app.DaoImpl.OwnerDaoImpl;
import src.rutina.app.Objects.Owner;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;

/*
 * Clase que representa un controlador REST de propeitarios. Mapea las 
 * operaciones sobre recursos REST relacionados con eventos y hace uso 
 * del DAO para hacerlas efectivas en la base de datos.
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */

@RestController
@ImportResource("classpath:spring/config/beanLocations.xml")
public class OwnerController {

    // Obtenemos el DAO mediante inyección de dependencias
    @Autowired
    private OwnerDaoImpl ownerDao;

    // Obtiene un propietario
    @RequestMapping(value = UriConstants.USUARIO, method = RequestMethod.GET)
    public @ResponseBody List<Owner> getOwner(
	    @PathVariable("owner_id") String ownerId) {

	return this.ownerDao.getOwner(ownerId);
    }

    // Obtiene todos los propietarios
    @RequestMapping(value = UriConstants.ALL_USUARIOS, method = RequestMethod.GET)
    public @ResponseBody List<Owner> getAllOwners() {

	return this.ownerDao.getAllOwners();
    }

    // Elimina un propietario
    @RequestMapping(value = UriConstants.USUARIO, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwner(@PathVariable("owner_id") String ownerId) {

	ownerDao.deleteOwner(ownerId);
    }

    // Elimina todos los propietarios
    @RequestMapping(value = UriConstants.ALL_USUARIOS, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllOwners() {

	ownerDao.deleteAllOwners();
    }

    // Añade un propietario
    @RequestMapping(value = UriConstants.USUARIOS_REGISTER, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addOwner(@RequestBody Owner owner) {

	ownerDao.createOwner(owner.getOwnerId(), owner.getOwnerName(),
		owner.getOwnerPhoneNumber(), owner.getOwnerBirthDate(),
		owner.getOwnerPassw());
    }
    
     //Modifica un propietario
    @RequestMapping(value = UriConstants.USUARIO, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOwner(@PathVariable("owner_id") String ownerId,
	    @RequestBody Owner owner) {

	ownerDao.updateOwner(ownerId, owner.getOwnerName(),
		owner.getOwnerPhoneNumber(), owner.getOwnerBirthDate(),
		owner.getOwnerPassw());
    }
}
