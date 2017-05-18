
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
	$('#ejercicio_form').validate({
		// Establecemos las reglas de validación para
		// cada uno de los campos del formulario
		rules : {
			ejercicio_nombre : {
				required : true,
				minlength : 2
			},
			ejercicio_titulo : {
				required : true,
				minlength : 2,
				maxlength : 200
			},			
			ejercicio_subtitulo: {
				required : true,
				minlength : 2
			},
			ejercicio_descripcion: {
				required : true,
				minlength : 2,
				maxlength : 200
			},
			ejercicio_estadoforma: {
				required : true
			},
			ejercicio_repeticiones: {
				required : true
			},
			ejercicio_repvideo:{
				required : true
			}
		},
		// Establecemos la función que se ejecutará en caso
		// de envío del formulario.
		submitHandler : function(form) {
			sendEjercicioData();
		}
	});

});


/* Evento que lanza el envío del formulario */
function submitForm() {
	$("#ejercicio_form").submit();
}


/* Función de extracción y envío de los datos del formulario */
function sendEjercicioData() {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));

	// Obtenemos los datos del evento del formulario
	var ejercicio_nombre = $('[name="ejercicio_nombre"]').val();
	var ejercicio_titulo = $('[name="ejercicio_titulo"]').val();
	var ejercicio_subtitulo = $('[name="ejercicio_subtitulo"]').val();
	var ejercicio_descripcion = $('[name="ejercicio_descripcion"]').val();
	var ejercicio_estadoforma = $('[name="ejercicio_estadoforma"]').val();
	var ejercicio_repeticiones = $('[name="ejercicio_repeticiones"]').val();
	var ejercicio_repvideo = $('[name="ejercicio_repvideo"]').val();

	
	
	// JSON formado con los datos del formulario extraídos
	var rutina_json = {
		ownerId : cookie.userid,
		ejercicioNombre: ejercicio_nombre,
		ejercicioTitulo : ejercicio_titulo,
		ejercicioSubtitulo: ejercicio_subtitulo,
		ejercicioDescripcion : ejercicio_descripcion,
		ejercicioEstado_Forma : ejercicio_estadoforma,
		ejercicioRepeticiones: ejercicio_repeticiones,
        ejercicioRep_Video: ejercicio_repvideo
	};
	
	// Añadimos el ejercicio a la base de datos
	$.ajax({
		url : "/Rutina_app/ejercicios/" + cookie.userid + "/",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "POST",
		data : JSON.stringify(rutina_json),
		contentType : "application/json",
	// En caso de éxito: informamos y redirigimos
	}).done(function (data, textStatus, jqXHR) {
		alert("Ejercicio añadido con éxito");
		window.location.href = "EjercicioMain.html";

	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}
