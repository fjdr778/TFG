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
	getAllEjerciciosData();
});


/* Función que obtiene los datos de todas los ejercicios de un propietario */
function getAllEjerciciosData() {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/ejercicios/" + cookie.userid + "/",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
	// En caso de éxito: imprimimos un resumen de los ejercicios
	}).done(function (data, textStatus, jqXHR) {
		printAllEjerciciosData(data);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}

/* Función que imprime un resumen de todos los ejercicios de un propietario 
   en una tabla */
function printAllEjerciciosData(jsonEjerciciosArray) {
	// Obtenemos el contenedor donde imprimiremos los ejercicios
	var container = $(".print-ejercicios")[0];
	// Iteramos para cada una de los ejercicios e imprimimos sus campos
	for (var i = 0; i < jsonEjerciciosArray.length; i++) {
		var obj = jsonEjerciciosArray[i];
		var summedEjerciciosInfo = "<tr>" + "<td>"
		+ obj.ejercicioNombre
		+ "</td>"
		+ "<td>"
		+ obj.ejercicioTitulo
		+ "</td>"
		+ "<td>"
		+ obj.ejercicioSubtitulo
		+ "</td>"
		+ "<td>"
		+ obj.ejercicioDescripcion
		+ "</td>"
		+ "<td>"
		+ obj.ejercicioEstado_Forma
		+ "</td>"
		+ "<td>"
		+ obj.ejercicioRep_Video
		+ "</td>"
		+ "<td>"
		+ "<td>"
		+ "<a href='EjercicioModify.html?ej_id="
		+ obj.ej_id
		+ "'><input type='button' class='mod-buttons' value='MODIFICAR' /></a>"		
		+ "<a onclick='deleteEjercicioData("
		+ obj.ej_id
		+ ")'><input type='button' class='del-buttons' value='ELIMINAR' /></a>"
		+ "</td>" + "</tr>"
		console.log(obj.ej_id);
		console.log(obj.ejercicioTitulo);
		container.innerHTML += summedEjerciciosInfo;
	}
}




/* Función que elimina los datos de la rutina de la base de datos */
function deleteEjercicioData(ej_id) {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/ejercicios/" + cookie.userid + "/"
				+ ej_id,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "DELETE",
	// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("Ejercicio borrado.");
		window.location.href = "EjercicioMain.html";
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}