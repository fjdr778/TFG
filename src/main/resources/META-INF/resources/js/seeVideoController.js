
    /*
 * Controlador de la página EjercicioMain.html
 * 
 * 
 * Diseño por Fco Jose Diaz Romero
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	repvideo();
});


/* Función que imprime un resumen de todos los ejercicios de un propietario 
   en una tabla */
function repvideo() {
	// Obtenemos el contenedor donde imprimiremos los ejercicios
	var container = $("#videCont")[0];

	var userid = getUrlParameter('user_id');
	var ej_id = getUrlParameter('ej_id');

	console.log(userid);
	console.log(ej_id);
			var summedSeeVideo = "<video controls>" 
			+ "<source src='http://localhost:8080/rutina_app/uploads/" 
			+ userid
			+ "_"
			+ ej_id
			+ ".mp4' type='video/mp4'>"
			+ "</video>"		
			container.innerHTML += summedSeeVideo;
			
}





