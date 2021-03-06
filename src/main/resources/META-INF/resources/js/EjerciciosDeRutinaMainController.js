/*
 * Controlador de la página EjerciciosDeRutinaMain.html
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {

	var rutPub=getUrlParameter('rutPub');

	if(rutPub=="nopub")
	{
		accionboton();
		getMisEjerciciosDeRutinaData();
	}
	else
	{
		$("#botbotbot").hide();
		getEjerciciosDeRutinaPublicosData();

	}	
});

/* Función que obtiene los datos de todas las todos los ejercicios asociados a una rutina*/
function getMisEjerciciosDeRutinaData() {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var rut_id = getUrlParameter('rut_Id');

	$.ajax({
		url : "/Rutina_app/rutinas/asociaciones/" + rut_id + "/"+"?rutPub=nopub",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
		// En caso de éxito: imprimimos un resumen de las asociaciones
	}).done(function (data, textStatus, jqXHR) {
		printMisEjerciciosDeRutinaData(data,rut_id);
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}

/* Función que obtiene los datos de todas las todos los ejercicios asociados a una rutina*/
function getEjerciciosDeRutinaPublicosData() {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var rut_id = getUrlParameter('rut_Id');

	$.ajax({
		url : "/Rutina_app/rutinas/asociaciones/" + rut_id + "/"+"?rutPub=pub",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
		// En caso de éxito: imprimimos un resumen de las asociaciones
	}).done(function (data, textStatus, jqXHR) {
		printEjerciciosDeRutinaPublicosData(data,rut_id);
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}

/* Función que imprime un resumen de todos los ejercicios asociados a una rutina
 * Si tiene asociados ejercicios se muestran, y si no, se imprime por pantalla un mensaje de aviso 
 * se permite añadir ejercicios.
 */
function printMisEjerciciosDeRutinaData(jsonEjerciciosArray,rut_id) {
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


/* Función que imprime un resumen de todos los ejercicios asociados a una rutina publica
 * Si tiene asociados ejercicios se muestran, y si no, se imprime por pantalla un mensaje de aviso 
 * se permite añadir ejercicios.
 */
function printEjerciciosDeRutinaPublicosData(jsonEjerciciosArray,rut_id) {
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
			+ "<td>"					
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
	var rutPub = getUrlParameter('rutPub');
	var cadena = "EjerciciosDeRutinaAsociate.html?rut_Id=" + rut_id + "&rutPub=" + rutPub;
	var boton = "<a href="+cadena+"><font color='white'>Asociar Ejercicios</font></a>";

	var container = $("#botbotbot");

	container.prepend(boton);
}



/* Función que elimina los datos de la rutina de la base de datos */
function deleteEjercicioDeRutina(RutinaId,EjercicioId) {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var rutPub=getUrlParameter('rutPub');

	$.ajax({
		url : "/Rutina_app/rutinas/asociaciones/" + RutinaId + "/"
		+ EjercicioId,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "DELETE",
		// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("ejercicio Desasociado.");
		window.location.href = "EjerciciosDeRutinaMain.html?rut_Id="+RutinaId+"&rutPub="+rutPub;
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}