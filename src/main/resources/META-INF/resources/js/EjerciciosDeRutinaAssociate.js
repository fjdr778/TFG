/*
 * Controlador de la página EjerciciosDeRutinaAsociate.html
 * 
 * 
 * Diseño por Fco Jose Diaz Romero
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	//accionboton();
	getAllEjerciciosData();
});


/* Función que obtiene los datos de todas los ejercicios no asociados a dicha rutina*/
function getAllEjerciciosData() {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var rut_id = getUrlParameter('rut_Id');
	$.ajax({
		url : "/Rutina_app/rutinas/noasociaciones/" + rut_id + "/",
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

/*function accionboton()
{

	var rut_id = getUrlParameter('rut_Id');

	var cadena = "EjerciciosDeRutinaMain.html?rut_Id=" + rut_id;
	var boton = "<a style='float:right' href="+cadena+"><< Volver Atras</a>";

	var container = $("#botbotbot");

	container.prepend(boton);
}*/

/* Función que imprime un resumen de todos los ejercicios de un propietario 
   en una tabla */
function printAllEjerciciosData(jsonEjerciciosArray) {
	// Obtenemos el contenedor donde imprimiremos los ejercicios
	var container = $(".print-ejerciciosderutina")[0];
	var rut_id = getUrlParameter('rut_Id');

	//compruebo si el json obtenido esta vacio:
	if (jsonEjerciciosArray.length == 0)
		{
			$(".print-ejerciciosderutina").hide();
			$("#text-info").show();
			$('#crea').show();
		}
	else
		{
			$(".print-ejerciciosderutina").show();
			$("#text-info").hide();
			$('#crea').hide();
	// Iteramos para cada una de los ejercicios e imprimimos sus campos
	for (var i = 0; i < jsonEjerciciosArray.length; i++) {
		var obj = jsonEjerciciosArray[i];
		
		var visibilidad;
		console.log(obj.ejercicioPub_Priv);
		if(obj.ejercicioPub_Priv==true)
		{
			visibilidad="Publica";
		}
		else
		{
			visibilidad="Privada";
		}
		console.log(visibilidad);
		
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
		+ obj.ejercicioRepeticiones
		+ "</td>"
		+ "<td>"
		+ visibilidad
		+ "</td>"		
		+ "<td>"
		+ "<a onclick='AsociateEjercicioData("
		+ rut_id
		+","
		+ obj.ej_id
		+ ")'><input type='button' class='del-buttons' value='ASOCIAR' /></a>"
		+ "</td>" + "</tr>"
		container.innerHTML += summedEjerciciosInfo;
	}
		}
}




/* Función que elimina los datos de la rutina de la base de datos */
function AsociateEjercicioData(rut_id,ej_id) {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/rutinas/asociaciones"+ "/" + rut_id + "/"
				+ ej_id,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "POST",
	// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("Ejercicio Asociado.");
		window.location.href = "EjerciciosDeRutinaMain.html?rut_Id="+rut_id;
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}