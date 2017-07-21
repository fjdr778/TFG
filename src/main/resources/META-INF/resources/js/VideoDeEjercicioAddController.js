/*
 * Controlador de la página VideosAdd.html
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */


$(document).ready(function() {

});

/* Evento que lanza el envío del formulario */
function submitForm() {
	$("#upload-file-input").submit();
	var ej_id = getUrlParameter('ej_id');
	uploadFile(ej_id);	
}

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile(ej_id) {
	console.log(ej_id);
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));  	
	console.log(cookie.userid);
	$.ajax({
		url: "/Rutina_app/videos/"+ cookie.userid + "/" + ej_id,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type: "POST",
		data: new FormData($("#upload-file-form")[0]),
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		cache: false,
		success: function () {
			alert("Video Asociado");
			window.location.href = "EjercicioMain.html?ejercicio_Pub_Priv=false";
		},
		error: function () {
			// Handle upload error
			alert("Video No Asociado");
			//window.location.href = "EjercicioMain.html";
		}
	});
} // function uploadFile




    
    
    