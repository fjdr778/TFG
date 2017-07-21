/*
 * Controlador de la página UserDropOut.html
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */


function deleteUserData() {

	// Obtenemos los datos de la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));

	// Borramos los datos del usuario de la base de datos
	$.ajax({
		url : "/Rutina_app/" + cookie.userid,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "DELETE",
		// En caso de éxito: informamos y redirigimos
		success : function() {
			alert("Perfil borrado. Baja dada con éxito.");
			window.location.href = "login.html";
		},
		// En caso de error: mostramos el error
		error : function(status, er) {
			alert("status: " + JSON.stringify(status) + " er:" + er);
		}
	});
}
