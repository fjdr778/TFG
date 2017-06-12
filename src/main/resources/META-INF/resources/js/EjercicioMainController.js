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

/* Función que obtiene los datos de todas las rutinas de un propietario */
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



/* Función que imprime un resumen de todos los ejercicios de un propietario 
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
	}
	else
	{
		$(".print-ejercicios").show();
		$("#text-info").hide();
		$(".leyenda").show();

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
				+ "'><i class='material-icons'>create</i></a>"	
				+ "<a href='VideosAdd.html?ej_id=" 
				+ obj.ej_id
				+ "'><i class='material-icons'>file_upload</i></a>"	
				+ "<a onclick='deleteEjercicioData("
				+ obj.ej_id
				+ ")'><i class='material-icons'>delete</i></a>"
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
				+ "'><i class='material-icons'>create</i></a>"

				+ "<a href='SeeVideo.html?user_id="
				+ cookie.userid
				+ "&ej_id="
				+ obj.ej_id
				+ "'><i class='material-icons'>video_library</i></a>"	
				+ "<a onclick='deleteVideoData("
				+ obj.ej_id
				+ ")'><i class='material-icons'>delete</i></a>"
				+ "</td>" + "</tr>"


				container.innerHTML += summedEjerciciosInfo;

			}
		}
	}
}




/* Función que imprime un resumen de todos los ejercicios de un propietario 
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
	}
	else
	{
		$(".print-ejercicios").show();
		$("#text-info").hide();
		$(".leyenda").show();

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
				+ "No tiene privilegios para ejercicios de otros usuarios"
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
				+ "'><i class='material-icons'>video_library</i></a>"					
				+ "</td>" + "</tr>"


				container.innerHTML += summedEjerciciosInfo;

			}
		}
	}
}




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
		window.location.href = "EjercicioMain.html";
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Error: Ejercicio asociado a alguna rutina.");
	});
}


/* Función que elimina los datos de la rutina de la base de datos */
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
		window.location.href = "EjercicioMain.html";
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}



