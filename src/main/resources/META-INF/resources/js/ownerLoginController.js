
/*
 * Controlador de la página ownerLogin.html
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
	$('#login_form').validate({
		// Establecemos las reglas de validación para
		// cada uno de los campos del formulario
		rules : {
			user_email : {
				required : true,
				email : true
			},
			user_password : {
				required : true,
				minlength : 5
			},
			/*user_password_confirm : {
				required : true,
				equalTo : "#user_password",
				minlength : 5
			}*/
		},
		// Establecemos la función que se ejecutará en caso
		// de envío del formulario.
		submitHandler : function(form) {
			sendOwnerData();
		}
	});

});


/* Evento que lanza el envío del formulario */
function submitForm() {
	$("#login_form").submit();
}


/* A diferencia del resto de funciones de envíar datos de usuario. ésta lo que
   hace es crear una cookie con el usuario autenticado para no hardcodear el
   usuario dentro del código y su obtención sea dinámica por parte del 
   programa,aunque pueda autenticarse cualquier usuario debido a que no 
   disponemos de sistema de autenticación.
 */
function sendOwnerData() {
	
	//showMeYourCookies('At loginform submission');
	
	// Obtenemos los datos del propietario del formulario
	var owner_Id = $('[name="user_email"]').val();
	var owner_Passw = $('[name="user_password"]').val();
	
	// Obtenemos la cookie de usuario
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	var data = 'username=' + owner_Id + '&password=' + owner_Passw;
	
	$.ajax({
		data: data,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		timeout: 1000,
		type: "POST",
		url: "/login"

	}).done(function(data, textStatus, jqXHR) {
		// No se informa de nada: 
		// La autenticación del usuario es autodescriptiva		
		// Almacenamos en la cookie RutinaUser la información del usuario
		var cookie = JSON.stringify({method: '', url: '/', csrf: jqXHR.getResponseHeader('X-CSRF-TOKEN'), userid: owner_Id});
		$.cookie('RutinaUsuario', cookie);
		
		// Obtenemos la cookie nueva
		var cookie = JSON.parse($.cookie('RutinaUsuario'));
		
		// Redireccionamos

		window.location.href = "index.html";

	}).fail(function(jqXHR, textStatus, errorThrown) {
		// Informamos de que ha fallado la autenticación
		alert("La contraseña o el dni son erróneos");
		// Redirigimos si recarga la página para recargar la cookie
		window.location.href = "index.html";
	});
}
