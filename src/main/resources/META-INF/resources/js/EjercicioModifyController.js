
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
	getEjercicioData();

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
			ejercicio_Pub_Priv: {
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
	
	// Obtenemos los parámetros de la URL
	var ej_id = getUrlParameter('ej_id');

	// Obtenemos los datos del ejercicio del formulario
	var ejercicio_nombre = $('[name="ejercicio_nombre"]').val();
	var ejercicio_titulo = $('[name="ejercicio_titulo"]').val();
	var ejercicio_subtitulo = $('[name="ejercicio_subtitulo"]').val();
	var ejercicio_descripcion = $('[name="ejercicio_descripcion"]').val();
	var ejercicio_estadoforma = $('[name="ejercicio_estadoforma"]').val();
	var ejercicio_repeticiones = $('[name="ejercicio_repeticiones"]').val();
	var ejercicio_Pub_Priv = $('[name="ejercicio_Pub_Priv"]').val();
	
	// JSON formado con los datos del formulario extraídos
	var ejercicio_json = {
		ownerId : cookie.userid,
		ejercicioNombre: ejercicio_nombre,
		ejercicioTitulo : ejercicio_titulo,
		ejercicioSubtitulo: ejercicio_subtitulo,
		ejercicioDescripcion : ejercicio_descripcion,
		ejercicioEstado_Forma : ejercicio_estadoforma,
		ejercicioRepeticiones: ejercicio_repeticiones,
		ejercicioPub_Priv: ejercicio_Pub_Priv
	};
	
	// Actualizamos la información de la Rutina en la base de datos
	$.ajax({
		url : "/Rutina_app/ejercicios/" + cookie.userid + "/" + ej_id + "?ejercicio_Pub_Priv=" + ejercicio_Pub_Priv,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "POST",
		data : JSON.stringify(ejercicio_json),
		contentType : "application/json",
	}).done(function (data, textStatus, jqXHR) {
		alert("Modificación realizada con éxito");
		window.location.href = "EjercicioMain.html";
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error");
	});
}


/* Función que obtiene los datos del ejercicio de la base de datos */
function getEjercicioData() {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));

	// Obtenemos los parámetros de la URL
	var ej_id = getUrlParameter('ej_id');

	// Obtenemos la información del evento de la base de datos
	$.ajax({
		url : "/Rutina_app/ejercicios/" + cookie.userid + "/" + ej_id,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
		cache : false,
	}).done(function (data, textStatus, jqXHR) {
		
		console.log(data[0].ejercicioNombre);

		// Imprimimos los datos en el HTML
		$('[name="ejercicio_nombre"]').val(data[0].ejercicioNombre);
		$('[name="ejercicio_titulo"]').val(data[0].ejercicioTitulo);
		$('[name="ejercicio_subtitulo"]').val(data[0].ejercicioSubtitulo);
		$('[name="ejercicio_descripcion"]').val(data[0].ejercicioDescripcion);
		$('[name="ejercicio_estadoforma"]').val(data[0].ejercicioEstado_Forma);
		$('[name="ejercicio_repeticiones"]').val(data[0].ejercicioRepeticiones);
		$('[name="ejercicio_Pub_Priv"]').val(data[0].ejercicioPub_Priv);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		console.log(ej_id);
		alert("Se ha producido un error.");
	});
}
