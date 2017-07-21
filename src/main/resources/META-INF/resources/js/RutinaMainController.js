
/*
 * Controlador de la página RutinaMain.html
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
	var rutina_Pub_Priv = false; 
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

	if (jsonRutinasArray.length == 0)
	{
		$(".print-rutinas").hide();
		$("#text-info").show();
		$(".leyenda").hide();
		cabeceraprivada();
	}
	else
	{
		$(".print-rutinas").show();
		$("#text-info").hide();
		$(".leyenda").show();
		cabeceraprivada();
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
			+ "'data-toggle='tooltip' title='Modificar Rutina'><i class='material-icons'>create</i></a>"
			+ "<a href='EjerciciosDeRutinaMain.html?rut_Id="//En este html, deben mostrarse los ejercicios de una rutina
			+ obj.rut_id
			+"&rutPub=nopub"
			+ "' data-toggle='tooltip' title='Ejercicios Asociados'><i class='material-icons'>assignment</i></a>"
			+ "<a onclick='downloadRutinaData("
			+ obj.rut_id
			+ ")' data-toggle='tooltip' title='Descargar Rutina'><i class='material-icons'>file_download</i></a>"
			+ "<a onclick='deleteRutinaData("
			+ obj.rut_id
			+ ")' data-toggle='tooltip' title='Eliminar Rutina'><i class='material-icons'>delete</i></a>"
			+ "</td>" + "</tr>"

			container.innerHTML += summedRutinaInfo;
			/*Arreglar la informacion de rutinas y ademas si es publica o privada*/

		}
	}
}

/* Función que imprime un resumen de todas rutinas publicas de otros usuarios
en una tabla */
function printRutinasPublicasData(jsonRutinasArray) {
	// Obtenemos el contenedor donde imprimiremos las rutinas
	var container = $(".print-rutinas")[0];

	//compruebo si el json obtenido esta vacio:
	if (jsonRutinasArray.length == 0)
	{
		$(".print-rutinas").hide();	
		$("#text-info").show();
		$(".leyenda").hide();
		cabecerapublica();
	}
	else
	{
		$(".print-rutinas").show();
		$("#text-info").hide();
		$(".leyenda").show();
		cabecerapublica();
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
			+"&rutPub=pub"
			+ "' data-toggle='tooltip' title='Ejercicios Asociados'><i class='material-icons'>assignment</i></a>"
			+ "<a onclick='downloadRutinaData1("
			+ obj.rut_id	
			+ ")' data-toggle='tooltip' title='Descargar Rutina'><i class='material-icons'>file_download</i></a>"
			+ "</td>" + "</tr>"

			container.innerHTML += summedRutinaInfo;
			/*Arreglar la informacion de rutinas y ademas si es publica o privada*/
			console.log(obj.rutinaNombre);
			console.log(obj.rutinaInfo_Rutina);
		}
	}
}

//Añade informacion de cabecera para las rutinas privadas
function cabeceraprivada()
{
	var cab= "<h2>" +
	"Mis Rutinas<small>Estas son las Rutinas creadas por el usuario que ha iniado sesión." +
	"Podemos ver tanto las rutinas públicas como privadas del usuario registrado con un resumen de sus datos." +
	"En el caso de que quiera añadir nuevas rutinas, o modificar" +
	"los ya existentes, deberá hacer uso de las opciones de debajo" +
	"de la lista.</small></h2>";

	var container = $(".cabecera");


	container.empty();
	container.prepend(cab);	
}

//Añade informacion de cabecera para las rutinas publicas
function cabecerapublica()
{
	var cab= "<h2>" +
	"Rutinas Públicas<small>Estas son las Rutinas creadas por otros usuarios que han decidido publicarlas." +
	"Podemos ver tanto las rutinas públicas usuario registrado como las rutinas públicas de los demás usuarios," +
	" con un resumen de sus datos." +
	"Se tienen menos privilegios sobre estas rutinas, como ver los ejercicios públicos que tiene asociados, Crear el .Zip " +
	"o descargar la rutina solo con sus ejercicios públicos." +
	"</small></h2>";

	var container = $(".cabecera");


	container.empty();
	container.prepend(cab);	
}

//Obtiene el campo para realizar la busqueda de una rutina.
function busqueda()
{
	var rutina_Pub_Priv = getUrlParameter('rutina_Pub_Priv');
	console.log(rutina_Pub_Priv);

	// Obtenemos los datos de la rutina del formulario
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

//Funcion para preparar la descarga de una rutina de un propiertario
function downloadRutinaData(RutinaId){
	var control=true;
	 control= getMisEjerciciosDeRutinaData(RutinaId);
	console.log(control);
	
	if(control==false){
		if(confirm("Esta rutina tiene algunos ejercicios sin videos asociados. si continua con la descarga, " +
				"no se añadiran aquellos ejercicios que carecen de videos")){
			// Obtenemos la cookie
			var cookie = JSON.parse($.cookie('RutinaUsuario'));
			$.ajax({
				url : "/Rutina_app/downloads/" + cookie.userid + "/"
				+ RutinaId + "?rutPub=nopub",
				headers: {'X-CSRF-TOKEN': cookie.csrf},
				type : "POST",
				async: false,
				// En caso de éxito: informamos y redirigimos
			}).done(function (data, textStatus, jqXHR) {
				window.location.href = "/rutina_app/zip/rutina_"+RutinaId+".zip";
				// Avisamos al usuario de que ha surgido un error
			}).fail(function (jqXHR, textStatus, errorThrown) {
				alert("No es posible descargar la rutina.");
			});					
		}
	}
	else{
		// Obtenemos la cookie
		var cookie = JSON.parse($.cookie('RutinaUsuario'));
		$.ajax({
			url : "/Rutina_app/downloads/" + cookie.userid + "/"
			+ RutinaId + "?rutPub=nopub",
			headers: {'X-CSRF-TOKEN': cookie.csrf},
			type : "POST",
			async: false,
			// En caso de éxito: informamos y redirigimos
		}).done(function (data, textStatus, jqXHR) {
			window.location.href = "/rutina_app/zip/rutina_"+RutinaId+".zip";
			alert("Rutina Descargada.");
			// Avisamos al usuario de que ha surgido un error
		}).fail(function (jqXHR, textStatus, errorThrown) {
			alert("No es posible descargar la rutina.");
		});		
	}
			
}

//Funcion para preparar la descarga de una rutina publica
function downloadRutinaData1(RutinaId){
	
	
	var control=true;
	 control= getEjerciciosDeRutinaPublicosData(RutinaId);
	console.log(control);
	
	if(control==false){
		if(confirm("Esta rutina tiene algunos ejercicios sin videos asociados. si continua con la descarga, " +
				"no se añadiran aquellos ejercicios que carecen de videos")){
			// Obtenemos la cookie
			var cookie = JSON.parse($.cookie('RutinaUsuario'));
			$.ajax({
				url : "/Rutina_app/downloads/" + cookie.userid + "/"
				+ RutinaId + "?rutPub=pub",
				headers: {'X-CSRF-TOKEN': cookie.csrf},
				type : "POST",
				async: false,
				// En caso de éxito: informamos y redirigimos
			}).done(function (data, textStatus, jqXHR) {
				window.location.href = "/rutina_app/zip/rutina_"+RutinaId+".zip";
				alert("Rutina Descargada.");
				// Avisamos al usuario de que ha surgido un error
			}).fail(function (jqXHR, textStatus, errorThrown) {
				alert("No es posible descargar la rutina.");
			});
		}
	}
	else
		{
		// Obtenemos la cookie
		var cookie = JSON.parse($.cookie('RutinaUsuario'));
		$.ajax({
			url : "/Rutina_app/downloads/" + cookie.userid + "/"
			+ RutinaId + "?rutPub=pub",
			headers: {'X-CSRF-TOKEN': cookie.csrf},
			type : "POST",
			async: false,
			// En caso de éxito: informamos y redirigimos
		}).done(function (data, textStatus, jqXHR) {
			window.location.href = "/rutina_app/zip/rutina_"+RutinaId+".zip";
			alert("Rutina Descargada.");
			// Avisamos al usuario de que ha surgido un error
		}).fail(function (jqXHR, textStatus, errorThrown) {
			alert("No es posible descargar la rutina.");
		});				
		}
	

}


/* Función que obtiene los datos de todas las todos los ejercicios asociados a una rutina
 * y comprueba si algun ejercicio no tiene video asociado*/
function getMisEjerciciosDeRutinaData(RutinaId) {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var control=true;
	var resp;
	$.ajax({
		url : "/Rutina_app/rutinas/asociaciones/" + RutinaId + "/"+"?rutPub=nopub",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		async: false,
		dataType : "json",
		// En caso de éxito: imprimimos un resumen de las asociaciones
	}).done(function (data, textStatus, jqXHR) {
		resp=data;
		if(resp.length==0)
			{
				 control=false;
			}
		else{
			for(i=0;i<resp.length;i++){
				
				var obj = resp[i];

				var id=obj.ej_id;

				var videojson=getVideoData(id);
				if(videojson.length==0){
				control=false;
				}

			}
			}
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
	return control;
}

/* Función que obtiene los datos de todas las todos los ejercicios asociados a una rutina
 * y comprueba si algun ejercicio no tiene video asociado*/
function getEjerciciosDeRutinaPublicosData(RutinaId) {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var control=true;
	var resp;


	$.ajax({
		url : "/Rutina_app/rutinas/asociaciones/" + RutinaId + "/"+"?rutPub=pub",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		async: false,
		dataType : "json",
		// En caso de éxito: imprimimos un resumen de las asociaciones
	}).done(function (data, textStatus, jqXHR) {
		resp=data;
		if(resp.length==0)
			{
				
				 control=false;
			}
		else{
			for(i=0;i<resp.length;i++){
				
				var obj = resp[i];
				console.log(obj.ej_id)
				console.log(control);
				var id=obj.ej_id;

				var videojson=getVideoData(id);
				if(videojson.length==0){
				console.log(control);
				control=false;
				}

			}
		}	
		// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
	console.log(control);
	return control;
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
function deleteRutinaData(RutinaId) {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));

	if(confirm("La Rutina que desea borrar tiene Ejercicios Asociados. ¿Esta seguro que desea borrar dicha rutina?"))
	{
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
			alert("Rutina Borrada");
		});
	}
	else
	{

	}
}