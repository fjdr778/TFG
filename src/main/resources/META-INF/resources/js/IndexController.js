
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
