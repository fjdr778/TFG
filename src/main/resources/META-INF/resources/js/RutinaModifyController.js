
/*
 * Controlador de la página localModify.html
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	// Obtenemos los datos de la Rutina de la base de datos
	getRutinaData();

	// Inicializamos el plugin de validación
	$('#rutina_form').validate({
		// Establecemos las reglas de validación para
		// cada uno de los campos del formulario
		rules : {
			rutina_name : {
				required : true,
				minlength : 2
			},
			rutina_description : {
				required : true,
				minlength : 20,
				maxlength : 200
			},			
			rutina_info: {
				required : true
			}
		},
		// Establecemos la función que se ejecutará en caso
		// de envío del formulario.
		submitHandler : function(form) {
			sendRutinaData();
		}
	});

});


/* Evento que lanza el envío del formulario */
function submitForm() {
	$("#rutina_form").submit();
}


/* Función de extracción y envío de los datos del formulario */
function sendRutinaData() {

	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	// Obtenemos los parámetros de la URL
	var rut_id = getUrlParameter('rut_id');

	// Obtenemos los datos del evento del formulario
	var rutina_name = $('[name="rutina_name"]').val();
	var rutina_description = $('[name="rutina_description"]').val();
	var rutina_info = $('[name="rutina_info"]').val();
	
	// JSON formado con los datos del formulario extraídos
	var rutina_json = {
		ownerId : cookie.userid,
		rutinaNombre : rutina_name,
		rutinaDescripcion : rutina_description,
		rutinaInfo_Rutina : rutina_info,
	};
	
	// Actualizamos la información de la Rutina en la base de datos
	$.ajax({
		url : "/Rutina_app/rutinas/" + cookie.userid + "/"
				+ rut_id,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "POST",
		data : JSON.stringify(rutina_json),
		contentType : "application/json",
	}).done(function (data, textStatus, jqXHR) {
		alert("Modificación realizada con éxito");
		window.location.href = "RutinaMain.html";
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}


/* Función que obtiene los datos de la rutina de la base de datos */
function getRutinaData() {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));

	// Obtenemos los parámetros de la URL
	var rut_id = getUrlParameter('rut_id');

	// Obtenemos la información del evento de la base de datos
	$.ajax({
		url : "/Rutina_app/rutinas/" + cookie.userid + "/"
				+ rut_id,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
		cache : false,
	}).done(function (data, textStatus, jqXHR) {
		// Imprimimos los datos en el HTML
		$('[name="rutina_name"]').val(data[0].rutinaNombre);
		$('[name="rutina_description"]').val(data[0].rutinaDescripcion);
		$('[name="rutina_info"]').val(data[0].rutinaInfo_Rutina);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}
