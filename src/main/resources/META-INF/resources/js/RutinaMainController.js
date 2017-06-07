
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

	var rutina_Pub_Priv = false 
	rutina_Pub_Priv= getUrlParameter('rutina_Pub_Priv');
	console.log(rutina_Pub_Priv);
	if(rutina_Pub_Priv=="false")
		{
			getMisRutinasData("");
		}
	else
		{
			getRutinasPublicas("");
		
		}

});



function busqueda()
{
	var rutina_Pub_Priv = getUrlParameter('rutina_Pub_Priv');
	console.log(rutina_Pub_Priv);
		
	// Obtenemos los datos del evento del formulario
	var rutina_busqueda = $('[name="rutina_busqueda"]').val();
	console.log(rutina_busqueda);
	

	$('.print-rutinas tbody tr').slice(0).remove();
	
	if(rutina_Pub_Priv=="false")
	{
		getMisRutinasData(rutina_busqueda);
	}
	else
	{
		getRutinasPublicas(rutina_busqueda);
	
	}
	
}

 










/* Función que obtiene los datos de todas las rutinas de un propietario */
function getMisRutinasData( busqueda) {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/rutinas/" + cookie.userid + "/" + "?rutina_Pub_Priv=false&rutina_busqueda="+busqueda,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
	// En caso de éxito: imprimimos un resumen de las rutinas
	}).done(function (data, textStatus, jqXHR) {
		printMisRutinasData(data);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}

/* Función que obtiene los datos de todas las rutinas de un propietario */
function getRutinasPublicas(busqueda) {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/rutinas/" + cookie.userid + "/" + "?rutina_Pub_Priv=true&rutina_busqueda="+busqueda,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
	// En caso de éxito: imprimimos un resumen de las rutinas
	}).done(function (data, textStatus, jqXHR) {
		printRutinasPublicasData(data);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}


/* Función que imprime un resumen de todas rutinas de un propietario 
   en una tabla */
function printMisRutinasData(jsonRutinasArray) {
	// Obtenemos el contenedor donde imprimiremos los locales
	var container = $(".print-rutinas")[0];

	//compruebo si el json obtenido esta vacio:
	if (jsonRutinasArray.length == 0)
		{
			$(".print-rutinas").hide();
			$("#text-info").show();
		}
	else
		{
			$(".print-rutinas").show();
			$("#text-info").hide();
	// Iteramos para cada una de las rutinas e imprimimos sus campos
	for (var i = 0; i < jsonRutinasArray.length; i++) {
		var obj = jsonRutinasArray[i];
		var visibilidad;

		if(obj.rutinaPub_Priv==true)
		{
		 visibilidad="Publica";
		}
		else
		{
		visibilidad="Privada";
		}

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
		+ visibilidad
		+ "</td>"
		+ "<td>"
		+ "<a href='RutinaModify.html?rut_id="
		+ obj.rut_id
		+ "'><i class='material-icons'>create</i></a>"
		+ "<a href='EjerciciosDeRutinaMain.html?rut_Id="//En este html, deben mostrarse los ejercicios de una rutina
		+ obj.rut_id
		+ "'><i class='material-icons'>assignment</i></a>"
		+ "<a onclick='downloadRutinaData("
		+ obj.rut_id	
		+ ")' download><i class='material-icons'>note_add</i></a>"
		+ "<a href='http://localhost:8080/rutina_app/zip/rutina_"
		+ obj.rut_id
		+".zip' download><i class='material-icons'>file_download</i></a>"
		+ "<a onclick='deleteRutinaData("
		+ obj.rut_id
		+ ")'><i class='material-icons'>delete</i></a>"
		+ "</td>" + "</tr>"
		
		container.innerHTML += summedRutinaInfo;
		/*Arreglar la informacion de rutinas y ademas si es publica o privada*/
		
	}
		}
}


/* Función que imprime un resumen de todas rutinas de un propietario 
en una tabla */
function printRutinasPublicasData(jsonRutinasArray) {
	// Obtenemos el contenedor donde imprimiremos los locales
	var container = $(".print-rutinas")[0];
	
	//compruebo si el json obtenido esta vacio:
	if (jsonRutinasArray.length == 0)
		{
			$(".print-rutinas").hide();
			$("#text-info").show();
		}
	else
		{
			$(".print-rutinas").show();
			$("#text-info").hide();
	// Iteramos para cada una de las rutinas e imprimimos sus campos
	for (var i = 0; i < jsonRutinasArray.length; i++) {
		var obj = jsonRutinasArray[i];
		var visibilidad;

		if(obj.rutinaPub_Priv==true)
		{
		 visibilidad="Publica";
		}
		else
		{
		visibilidad="Privada";
		}

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
		+ visibilidad
		+ "</td>"
		+ "<td>"
		+ "<a href='EjerciciosDeRutinaMain.html?rut_Id="//En este html, deben mostrarse los ejercicios de una rutina
		+ obj.rut_id
		+ "'><i class='material-icons'>assignment</i></a>"
		+ "<a onclick='downloadRutinaData("
		+ obj.rut_id	
		+ ")' download><i class='material-icons'>note_add</i></a>"
		+ "<a href='http://localhost:8080/rutina_app/zip/rutina_"
		+ obj.rut_id
		+".zip' download><i class='material-icons'>file_download</i></a>"
		+ "</td>" + "</tr>"
		
		container.innerHTML += summedRutinaInfo;
		/*Arreglar la informacion de rutinas y ademas si es publica o privada*/
		console.log(obj.rutinaNombre);
		console.log(obj.rutinaInfo_Rutina);
	}
		}
}


function downloadRutinaData(RutinaId){
	
	//Queda por configurar bien. Nose si es Get o Post.
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/downloads/" + cookie.userid + "/"
				+ RutinaId,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "POST",
	// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("Zip Creado.");
		//descargareal(RutinaId);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Zip no creado");
	});


}

function descargareal(RutId)
{
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	$.ajax({
		url : "/Rutina_app/downloads/" + cookie.userid + "/"
				+ RutId,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		contentType: 'application/zip',
		dataType: 'binary',
	// En caso de éxito: informamos y redirigimos
       success: function(result) {
    	   var url = URL.createObjectURL(result);
    	      var $a = $('<a />', {
    	        'href': url,
    	        'download': 'rutina_1.zip',
    	        'text': "click"
    	      }).hide().appendTo("body")[0].click();
       }
	
	/*done(function (data, textStatus, jqXHR) {
		alert("descarga.");
		console.log(data);
		window.location.href = "RutinaMain.html";
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("no descarga");*/
	});
	
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
		alert("La Rutina que desea borrar tiene Ejercicios Asociados. Por favor, desasocie los ejercicios antes de eliminar la Rutina");
	});
}