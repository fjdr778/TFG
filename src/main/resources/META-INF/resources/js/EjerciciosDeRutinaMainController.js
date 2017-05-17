/*
 * Controlador de la página EjerciciosDeRutinaMain.html
 * 
 * 
 * Diseño por Fco Jose Diaz Romero
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	accionboton();
	getAllEjerciciosDeRutinaData();
	
});


/* Función que obtiene los datos de todas las todos los ejercicios asociados a una rutina*/
function getAllEjerciciosDeRutinaData() {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var rut_id = getUrlParameter('rut_Id');
	
	$.ajax({
		url : "/Rutina_app/rutinas/asociaciones/" + rut_id + "/",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
	// En caso de éxito: imprimimos un resumen de las asociaciones
	}).done(function (data, textStatus, jqXHR) {
		printAllEjerciciosDeRutinaData(data,rut_id);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}


/* Función que imprime un resumen de todos los ejercicios asociados a una rutina
 * Si tiene asociados ejercicios se muestran, y si no, se imprime por pantalla un mensaje de aviso 
 * se permite añadir ejercicios.
 */
function printAllEjerciciosDeRutinaData(jsonEjerciciosArray,rut_id) {
	// Obtenemos el contenedor donde imprimiremos los ejercicios asociados.
	var container = $(".print-ejerciciosderutina")[0];
	// Iteramos para cada una de las rutinas e imprimimos sus campos
	
	//compruebo si el json obtenido esta vacio:
	if (jsonEjerciciosArray.length == 0)
		{
			$(".print-ejerciciosderutina").hide();
			$("#text-info").show();
		}
	else
		{
			$(".print-ejerciciosderutina").show();
			$("#text-info").hide();
			
			//Muestro todos los ejercicios asociados:
			
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
				+ "<a onclick='deleteEjercicioDeRutina("
				+ rut_id
				+","
				+ obj.ej_id
				+ ")'><input type='button' class='del-buttons' value='ELIMINAR' /></a>"
				+ "</td>" + "</tr>"
				
				console.log(rut_id);
				console.log(obj.ej_id);
				
				container.innerHTML += summedEjerciciosInfo;
			}
			
			
			
			
		}
	
	
		
}

function accionboton()
{

	var rut_id = getUrlParameter('rut_Id');

	var cadena = "EjerciciosDeRutinaAsociate.html?rut_Id=" + rut_id;
	var boton = "<a href="+cadena+">Asociar Ejercicios</a>";

	var container = $("#botbotbot");

	container.prepend(boton);
}



/* Función que elimina los datos de la rutina de la base de datos */
function deleteEjercicioDeRutina(RutinaId,EjercicioId) {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/rutinas/asociaciones/" + RutinaId + "/"
				+ EjercicioId,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "DELETE",
	// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("ejercicio Desasociado.");
		window.location.href = "EjerciciosDeRutinaMain.html?rut_Id="+RutinaId;
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}