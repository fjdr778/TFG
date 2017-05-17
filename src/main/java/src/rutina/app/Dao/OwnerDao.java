package src.rutina.app.Dao;

import java.sql.Date;
import java.util.List;

import src.rutina.app.Objects.Owner;

/*
 * Clase que representa la interfaz DAO de propietarios. Abstrae las 
 * operaciones necesarias sobre la base de datos relacionadas con los 
 * propietarios a petición del controlador REST de propietarios.
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */

public abstract interface OwnerDao {
    // Por defecto los propietarios van a crearse con un rol predeterminado 
    // ROLE_USER hasta que tengamos disponible la interfaz de gestión de 
    // administrador
    // También se habilitan todos por defecto, ya que esto es una función que
    // se encontrará disponible de manera funcional próximamente:
    // ENABLED = 1;
    public abstract void createOwner(String ownerId, String ownerName,
	    String ownerPhoneNumber, Date ownerBirthDate, String ownerPassw);
    public abstract void updateOwner(String ownerId, String ownerName,
	    String ownerPhoneNumber, Date ownerBirthDate, String ownerPassw);
    public abstract List<Owner> getOwner(String ownerId);
    public abstract List<Owner> getAllOwners();
    public abstract void deleteOwner(String ownerId);
    public abstract void deleteAllOwners();
}
