
/*
 * Controlador de la página localAdd.html
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
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
	
	// Añadimos el local a la base de datos
	$.ajax({
		url : "/Rutina_app/rutinas/" + cookie.userid + "/",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "POST",
		data : JSON.stringify(rutina_json),
		contentType : "application/json",
	// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("Rutina añadida con éxito");
		window.location.href = "RutinaMain.html";

	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}
