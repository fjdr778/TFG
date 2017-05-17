package src.rutina.app.Objects;

import java.sql.Date;

/*
 * Clase que representa un propietario en la aplicación.
 * 
 * 
 * Diseño: Francisco Jose Diaz Romero
 * All rights reserved
 *
 */

public class Owner {
    // El ownerId es el correo electrónico del propietario
    private String ownerId, ownerName, ownerPhoneNumber, ownerPassw;
    private Date ownerBirthDate;

    public Owner() {
    }

    public Owner(String ownerId, String ownerName, String ownerPhoneNumber,
	    String ownerPassw, Date ownerBirthDate) {
	this.ownerId = ownerId;
	this.ownerName = ownerName;
	this.ownerPhoneNumber = ownerPhoneNumber;
	this.ownerPassw = ownerPassw;
	this.ownerBirthDate = ownerBirthDate;
    }

    public String getOwnerId() {
	return ownerId;
    }

    public void setOwnerId(String ownerId) {
	this.ownerId = ownerId;
    }

    public String getOwnerName() {
	return ownerName;
    }

    public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
	return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
	this.ownerPhoneNumber = ownerPhoneNumber;
    }
    
    public String getOwnerPassw() {
	return ownerPassw;
    }

    public void setOwnerPassw(String ownerPassw) {
	this.ownerPassw = ownerPassw;
    }

    public Date getOwnerBirthDate() {
	return ownerBirthDate;
    }

    public void setOwnerBirthDate(Date ownerBirthDate) {
	this.ownerBirthDate = ownerBirthDate;
    }
}