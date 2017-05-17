
/*
 * Controlador de la página RutinaMain.html
 * 
 * 
 * Diseño por Fco Jose Diaz Romero
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	getAllRutinasData();
});


/* Función que obtiene los datos de todas las rutinas de un propietario */
function getAllRutinasData() {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/rutinas/" + cookie.userid + "/",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
	// En caso de éxito: imprimimos un resumen de las rutinas
	}).done(function (data, textStatus, jqXHR) {
		printAllRutinasData(data);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}


/* Función que imprime un resumen de todas rutinas de un propietario 
   en una tabla */
function printAllRutinasData(jsonRutinasArray) {
	// Obtenemos el contenedor donde imprimiremos los locales
	var container = $(".print-rutinas")[0];
	// Iteramos para cada una de las rutinas e imprimimos sus campos
	for (var i = 0; i < jsonRutinasArray.length; i++) {
		var obj = jsonRutinasArray[i];
		var summedRutinaInfo = "<tr>" + "<td>"
		+ obj.rutinaNombre
		+ "</td>"
		+ "<td>"
		+ obj.rutinaDescripcion
		+ "</td>"
		+ "<td>"
		+ obj.rutinaInfo_Rutina
		+ "</td>"
		+ "<td>"
		+ "<a href='RutinaModify.html?rut_id="
		+ obj.rut_id
		+ "'><input type='button' class='mod-buttons' value='MODIFICAR' /></a>"
		
		+ "<a href='EjerciciosDeRutinaMain.html?rut_Id="//En este html, deben mostrarse los ejercicios de una rutina
		+ obj.rut_id
		+ "'><input type='button' class='mod-buttons' value='EJERCICIOS ASOCIADOS' /></a>"
		
		+ "<a onclick='deleteRutinaData("
		+ obj.rut_id
		+ ")'><input type='button' class='del-buttons' value='ELIMINAR' /></a>"
		+ "</td>" + "</tr>"
		
		container.innerHTML += summedRutinaInfo;
		/*Arreglar la informacion de rutinas y ademas si es publica o privada*/
		console.log(obj.rutinaNombre);
		console.log(obj.rutinaInfo_Rutina);
		console.log(obj.ownerId);
	}
}




/* Función que elimina los datos de la rutina de la base de datos */
function deleteRutinaData(RutinaId) {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/rutinas/" + cookie.userid + "/"
				+ RutinaId,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "DELETE",
	// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("Rutina borrada.");
		window.location.href = "RutinaMain.html";
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}