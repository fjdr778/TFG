package src.rutina.app.Objects;

import java.sql.Date;

/*
 * Clase que representa un usuario en la aplicación.
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 */

public class Usuario {

	// El userId es el correo electrónico del Usuario

	private String userId, userName, userPhoneNumber, userPassw;
	private Date userBirthDate;

	public Usuario() {
	}

	public Usuario(String userId, String userName, String userPhoneNumber,
			String userPassw, Date userBirthDate) {
		this.userId = userId;
		this.userName = userName;
		this.userPhoneNumber = userPhoneNumber;
		this.userPassw = userPassw;
		this.userBirthDate = userBirthDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserPassw() {
		return userPassw;
	}

	public void setUserPassw(String userPassw) {
		this.userPassw = userPassw;
	}

	public Date getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}
}