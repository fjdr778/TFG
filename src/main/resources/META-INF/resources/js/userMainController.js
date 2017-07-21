/*
 * Controlador de la página UserMain.html
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	// Debemos hacer dos consultas: una para obtener el token y otra
	// para obtener los datos de usuario	
	getUserData();
});

/* Función que obtiene e  imprime los datos del propietario */
function getUserData() {

	// Obtenemos la cookie de usuario
	var cookie = JSON.parse($.cookie('RutinaUsuario'));

	$.ajax({
		url : "/Rutina_app/" + cookie.userid,
		type: 'GET',
		dataType : "json",
	}).done(function (data, textStatus, jqXHR) {
		var csrfToken = jqXHR.getResponseHeader('X-CSRF-TOKEN');
		if (csrfToken) {
			var cookie = JSON.parse($.cookie('RutinaUsuario'));
			cookie.csrf = csrfToken;
			$.cookie('RutinaUsuario', JSON.stringify(cookie));
		}
		$('[name="user_email"]').val(data[0].userId);
		$('[name="user_name"]').val(data[0].userName);
		$('[name="user_birthdate"]').val(data[0].userBirthDate);
		$('[name="user_phonenumber"]').val(data[0].userPhoneNumber);
	}).fail(function (jqXHR, textStatus, errorThrown) {
		if (jqXHR.status === 401) { // HTTP Status 401: Unauthorized
			var cookie = JSON.stringify({method: '', url: '/', csrf: jqXHR.getResponseHeader('X-CSRF-TOKEN')});
			$.cookie('RutinaUsuario', cookie);
			window.location = '/login.html';
		} else {
			alert("Ha habido un error en la obtención de datos de usuario11.");
		}
	});

}


