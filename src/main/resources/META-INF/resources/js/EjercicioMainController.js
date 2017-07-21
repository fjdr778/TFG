/*
 * Controlador de la página EjercicioMain.html
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {

	$('[data-toggle="tooltip"]').tooltip();
	var ejercicio_Pub_Priv = false;
	ejercicio_Pub_Priv = getUrlParameter('ejercicio_Pub_Priv');
	console.log(ejercicio_Pub_Priv);
	if(ejercicio_Pub_Priv=="false")
	{
		getMisEjerciciosData("");
	}
	else
	{
		getEjerciciosPublicos("");

	}
});


/* Función que obtiene los datos de todas los ejercicios de un propietario */
function getMisEjerciciosData(busqueda) {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));


	$.ajax({
		url : "/Rutina_app/ejercicios/" + cookie.userid + "/" + "?ejercicio_Pub_Priv=false&ejercicio_busqueda="+busqueda,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
		// En caso de éxito: imprimimos un resumen de los ejercicios
	}).done(function (data, textStatus, jqXHR) {
		printMisEjerciciosData(data);
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}

/* Función que obtiene los datos de todos los ejercicios de un propietario */
function getEjerciciosPublicos(busqueda) {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));


	$.ajax({
		url : "/Rutina_app/ejercicios/" + cookie.userid + "/" + "?ejercicio_Pub_Priv=true&ejercicio_busqueda="+busqueda,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
		// En caso de éxito: imprimimos un resumen de los ejercicios
	}).done(function (data, textStatus, jqXHR) {
		printEjerciciosPublicosData(data);
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}



/* Función que imprime un resumen de todos los ejercicios de un usuario 
   en una tabla */
function printMisEjerciciosData(jsonEjerciciosArray) {

	// Obtenemos el contenedor donde imprimiremos los ejercicios
	var container = $(".print-ejercicios")[0];

	//compruebo si el json obtenido esta vacio:
	if (jsonEjerciciosArray.length == 0)
	{
		$(".print-ejercicios").hide();
		$("#text-info").show();
		$(".leyenda").hide();
		cabeceraprivada();
	}
	else
	{
		$(".print-ejercicios").show();
		$("#text-info").hide();
		$(".leyenda").show();
		cabeceraprivada();

		// Iteramos para cada una de los ejercicios e imprimimos sus campos
		for (var i = 0; i < jsonEjerciciosArray.length; i++) {
			var obj = jsonEjerciciosArray[i];
			var jsonvideo=getVideoData(obj.ej_id);

			var cookie = JSON.parse($.cookie('RutinaUsuario'));

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
			if(jsonvideo.length == 0){

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
				+ "<a href='EjercicioModify.html?ej_id="
				+ obj.ej_id
				+ "' data-toggle='tooltip' title='Modificar Ejercicio'><i class='material-icons'>create</i></a>"	
				+ "<a href='VideosAdd.html?ej_id=" 
				+ obj.ej_id
				+ "' data-toggle='tooltip' title='Subir Video'><i class='material-icons'>file_upload</i></a>"	
				+ "<a onclick='deleteEjercicioData("
				+ obj.ej_id
				+ ")' data-toggle='tooltip' title='Eliminar Ejercicio'><i class='material-icons'>delete</i></a>"
				+ "</td>" + "</tr>"


				container.innerHTML += summedEjerciciosInfo;


			}
			else
			{
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
				+ "<a href='EjercicioModify.html?ej_id="
				+ obj.ej_id
				+ "' data-toggle='tooltip' title='Modificar Ejercicio'><i class='material-icons'>create</i></a>"

				+ "<a href='SeeVideo.html?user_id="
				+ cookie.userid
				+ "&ej_id="
				+ obj.ej_id
				+ "' data-toggle='tooltip' title='Ver Video'><i class='material-icons'>video_library</i></a>"	
				+ "<a onclick='deleteVideoData("
				+ obj.ej_id
				+ ")' data-toggle='tooltip' title='Eliminar Video'><i class='material-icons'>delete</i></a>"
				+ "</td>" + "</tr>"


				container.innerHTML += summedEjerciciosInfo;

			}
		}
	}
}

/* Función que imprime un resumen de todos los ejercicios publicos de otros usuarios 
en una tabla */
function printEjerciciosPublicosData(jsonEjerciciosArray) {

	// Obtenemos el contenedor donde imprimiremos los ejercicios
	var container = $(".print-ejercicios")[0];

	//compruebo si el json obtenido esta vacio:
	if (jsonEjerciciosArray.length == 0)
	{
		$(".print-ejercicios").hide();
		$("#text-info").show();
		$(".leyenda").hide();
		cabecerapublica();
	}
	else
	{
		$(".print-ejercicios").show();
		$("#text-info").hide();
		$(".leyenda").show();
		cabecerapublica();

		// Iteramos para cada una de los ejercicios e imprimimos sus campos
		for (var i = 0; i < jsonEjerciciosArray.length; i++) {
			var obj = jsonEjerciciosArray[i];
			var jsonvideo=getVideoData(obj.ej_id);

			var cookie = JSON.parse($.cookie('RutinaUsuario'));

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
			if(jsonvideo.length == 0){

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
				+ "Este ejercicio no tiene videos disponibles."
				+ "</td>" + "</tr>"


				container.innerHTML += summedEjerciciosInfo;


			}
			else
			{
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
				+ "<a href='SeeVideo.html?user_id="
				+ cookie.userid
				+ "&ej_id="
				+ obj.ej_id
				+ "' data-toggle='tooltip' title='Ver Video'><i class='material-icons'>video_library</i></a>"					
				+ "</td>" + "</tr>"


				container.innerHTML += summedEjerciciosInfo;

			}
		}
	}
}

//Añade informacion de cabecera para los ejercicios privados
function cabeceraprivada()
{
	var cab= "<h2>" +
	"Mis Ejercicios<small>Estos son los Ejercicios creados por el usuario que ha iniado sesion." +
	"Podemos ver tanto los ejercicios públicos como privados del usuario registrado con un resumen de sus datos." +
	"En caso de que quiera añadir nuevos ejercicios, o modificar los ya existentes, deberá hacer uso de las" +
	"opciones de debajo de la lista"                        
	"</small></h2>";

	var container = $(".cabecera");


	container.empty();
	container.prepend(cab);	
}

//Añade informacion de cabecera para las ejercicios publicos
function cabecerapublica()
{
	var cab= "<h2>" +
	"Ejercicios Públicos<small>Estas son los Ejercicios creados por otros usuarios que han decidido publicarlas." +
	"Podemos ver tanto los ejercicios públicos del usuario registrado como los ejercicios públicos de los demás usuarios," +
	" con un resumen de sus datos." +
	"Solo se tiene acceso a la visualiacion de los videos asigandos a cada ejercicio. Recordar que si un ejercicio no" +
	"tiene asociado videos, este no puede ser usado para una rutina." +
	"</small></h2>";

	var container = $(".cabecera");


	container.empty();
	container.prepend(cab);	
}

//Obtiene el campo para realizar la busqueda de un ejercicio.
function busqueda()
{
	var ejercicio_Pub_Priv = getUrlParameter('ejercicio_Pub_Priv');
	console.log(ejercicio_Pub_Priv);

	// Obtenemos los datos del evento del formulario
	var ejercicio_busqueda = $('[name="ejercicio_busqueda"]').val();
	console.log(ejercicio_busqueda);


	$('.print-ejercicios tbody tr').slice(0).remove();

	if(ejercicio_Pub_Priv=="false")
	{
		getMisEjerciciosData(ejercicio_busqueda);
	}
	else
	{
		getEjerciciosPublicos(ejercicio_busqueda);

	}

}

//IMORTANTE: Ajax es asincrono, lo cual debe ponerse sincrono para coger el valor
//de la respuesta correctamente y poder gestionarlo con JQuery
function getVideoData(ej_id){
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var json;

	$.ajax({
		url : "/Rutina_app/videos/" + cookie.userid + "/" + ej_id,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
		async:false,
		// En caso de éxito: imprimimos un resumen de los ejercicios
	}).done(function (data, textStatus, jqXHR) {
		//console.log(data);
		json=data;
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});	

	return json;
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
		window.location.href = "EjercicioMain.html?ejercicio_Pub_Priv=false";
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Error: Ejercicio asociado a alguna rutina.");
	});
}


/* Función que elimina los datos de un video asociado a un ejercicio de la base de datos */
function deleteVideoData(ej_id) {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));

	$.ajax({
		url : "/Rutina_app/videos/" + cookie.userid + "/"
		+ ej_id,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "DELETE",
		// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("Video borrado.");
		window.location.href = "EjercicioMain.html?ejercicio_Pub_Priv=false";
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}



