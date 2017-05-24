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

/* Función que imprime un resumen de todos los ejercicios de un propietario 
   en una tabla */
function printAllEjerciciosData(jsonEjerciciosArray) {
	// Obtenemos el contenedor donde imprimiremos los ejercicios
	var container = $(".print-ejercicios")[0];

	//compruebo si el json obtenido esta vacio:
	if (jsonEjerciciosArray.length == 0)
		{
			$(".print-ejercicios").hide();
			$("#text-info").show();
		}
	else
		{
			$(".print-ejercicios").show();
			$("#text-info").hide();
	
	// Iteramos para cada una de los ejercicios e imprimimos sus campos
	for (var i = 0; i < jsonEjerciciosArray.length; i++) {
		var obj = jsonEjerciciosArray[i];
		var jsonvideo=getVideoData(obj.ej_id);
		//console.log(jsonvideo);
		var cookie = JSON.parse($.cookie('RutinaUsuario'));
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
			+ obj.ejercicioRep_Video
			+ "</td>"
			+ "<td>"
			+ "<td>"
			+ "<a href='EjercicioModify.html?ej_id="
			+ obj.ej_id
			+ "'><input type='button' class='mod-buttons' value='MODIFICAR' /></a>"	
			+ "<a href='VideosAdd.html?ej_id=" 
			+ obj.ej_id
			+ "'><input type='button' class='mod-buttons' value='AÑADIR VIDEO' /></a>"	
			+ "<a onclick='deleteEjercicioData("
			+ obj.ej_id
			+ ")'><input type='button' class='del-buttons' value='ELIMINAR' /></a>"
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
			+ obj.ejercicioRep_Video
			+ "</td>"
			+ "<td>"
			+ "<td>"
			+ "<a href='EjercicioModify.html?ej_id="
			+ obj.ej_id
			+ "'><input type='button' class='mod-buttons' value='MODIFICAR' /></a>"
			
			+ "<video width='320' height='240' controls><source src='http://localhost:8080/rutina_app/uploads/"
			+ cookie.userid
			+ "_"
			+ obj.ej_id
			+ ".mp4' type='video/mp4'></video>"	
			+ "<input type='button' class='mod-buttons' value='VER VIDEO' /></a>"	
			+ "<a onclick='deleteVideoData("
			+ obj.ej_id
			+ ")'><input type='button' class='del-buttons' value='ELIMINAR VIDEO' /></a>"
			+ "</td>" + "</tr>"
		

			container.innerHTML += summedEjerciciosInfo;
			
		}
	}
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



