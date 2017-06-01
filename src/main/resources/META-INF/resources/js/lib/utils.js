
/*
 * Librería de manejo utilidades varias de la página web: incluye una función
 * de cierre de sesión y otra que extrae los parámetros de una URL dada.
 * 
 * Diseño por Fco Jose Diaz Romero
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Función que cierra la sesión de usuario */
function closeSession() {
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	$.ajax({
		data: {},
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		timeout: 1000,
		type: 'POST',
		url: '/logout'

	}).done(function(data, textStatus, jqXHR) {
		alert("Ha cerrado su sesión con éxito. Esperamos verle de nuevo.");
		window.location = '/login.html';

	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Ha habido un error en el cierre de su sesión.");
		window.location.href = "index.html";
	});
}

/* Función que extrae los parámetros que se pasan por URL entre páginas HTML */
function getUrlParameter(sParam) {
	// Los parámetros (sParam) deben serpararse por caracteres '&'
	var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
			.split('&'), sParameterName, i;
	// El valor del parámetro debe ir a continuación del mismo con un =
	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
	}
}
