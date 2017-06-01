
/*
 * Controlador de la página ownerMain.html
 * 
 * 
 * Diseño por Fco Jose Diaz Romero
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	// Debemos hacer dos consultas: una para obtener el token y otra
	// para obtener los datos de usuario	
	getToken();
});

/* Función que obtiene e  imprime los datos del propietario */
function getOwnerData() {
	
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
		$('[name="user_email"]').val(data[0].ownerId);
		$('[name="user_name"]').val(data[0].ownerName);
		$('[name="user_birthdate"]').val(data[0].ownerBirthDate);
		$('[name="user_phonenumber"]').val(data[0].ownerPhoneNumber);
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


/* Función que gestiona el Token de primera conexión */
function getToken() {
	
	$.ajax({
		url : "/Rutina_app/",
		type: 'GET',
	}).done(function (data, textStatus, jqXHR) {
		var csrfToken = jqXHR.getResponseHeader('X-CSRF-TOKEN');
		if (csrfToken) {
			var cookie = JSON.parse($.cookie('RutinaUsuario'));
			cookie.csrf = csrfToken;
			$.cookie('RutinaUsuario', JSON.stringify(cookie));
		}
		// Sólo una vez autenticados obtenemos los datos de usuario
		getOwnerData();
	}).fail(function (jqXHR, textStatus, errorThrown) {
		if (jqXHR.status === 401) { // HTTP Status 401: Unauthorized
			var cookie = JSON.stringify({method: '', url: '/', csrf: jqXHR.getResponseHeader('X-CSRF-TOKEN')});
			$.cookie('RutinaUsuario', cookie);
			window.location = '/login.html';
		} else {
			alert("Ha habido un error en la obtención de datos de usuario.");
		}
	});
}


