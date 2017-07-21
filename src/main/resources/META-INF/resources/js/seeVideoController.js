
/*
 * Controlador de la página SeeVideo.html
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	repvideo();
});


/* Funcion que permite la visualizacion de un video de un ejercicio */
function repvideo() {
	// Obtenemos el contenedor donde imprimiremos los ejercicios
	var container = $("#videCont")[0];

	var userid = getUrlParameter('user_id');
	var ej_id = getUrlParameter('ej_id');

	console.log(userid);
	console.log(ej_id);
	var summedSeeVideo = "<video controls>" 
		+ "<source src='/rutina_app/uploads/" 
		+ userid
		+ "_"
		+ ej_id
		+ ".mp4' type='video/mp4'>"
		+ "</video>"		
		container.innerHTML += summedSeeVideo;

}





