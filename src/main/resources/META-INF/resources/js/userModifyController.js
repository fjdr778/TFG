
/*
 * Controlador de la página UserModify.html
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	// Obtenemos los datos del usuario de la base de datos
	getUserData();
	// Inicializamos el plugin de validación
	$('#personal_form').validate({
		// Establecemos las reglas de validación para
		// cada uno de los campos del formulario
		rules : {
			user_email : {
				required : true,
				email : true
			},
			user_name : {
				required : true,
				minlength : 2
			},
			user_birthdate : {
				required : true
			},
			user_phonenumber : {
				required : false,
				maxlength: 9,
				minlength : 9
			},
			user_password : {
				required : true,
				minlength : 5
			},
			user_password_confirm : {
				required : true,
				equalTo : "#user_password",
				minlength : 5
			}
		},
		// Establecemos la función que se ejecutará en caso
		// de envío del formulario.
		submitHandler : function(form) {
			sendUserData();
		}
	});

});


/* Evento que lanza el envío del formulario */
function submitForm() {
	$("#personal_form").submit();
}


/* Función de extracción y envío de los datos del formulario */
function sendUserData() {

	// Obtenemos los datos del usuario del formulario
	var user_Id = $('[name="user_email"]').val();
	var user_Name = $('[name="user_name"]').val();
	var user_BirthDate = $('[name="user_birthdate"]').val();
	var user_PhoneNumber = $('[name="user_phonenumber"]').val();
	var user_Passw = $('[name="user_password"]').val();
	
	// JSON formado con los datos extraídos del formulario
	var user_json = {
		userId : user_Id,
		userName : user_Name,
		userBirthDate : user_BirthDate,
		userPhoneNumber: user_PhoneNumber,
		userPassw : user_Passw
	};
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	// Añadimos la información del usuario a la BBDD
	$.ajax({
		url : "/Rutina_app/" + cookie.userid,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "POST",
		data : JSON.stringify(user_json),
		contentType : "application/json",
		timeout: 1000
	}).done(function(data, textStatus, jqXHR) {
		// Reinicializamos el campo userid de la cookie, por si
		// el usuario lo ha modificado
		cookie.userid = user_Id;
		// Informamos de la operación y redirigimos
		alert("Modificación realizada con éxito");
		window.location.href = "index.html";
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});	
}



/* Función que obtiene los datos del usuario de la base de datos */
function getUserData() {
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	console.log(cookie.userid);

	// Obtenemos la información del usuario de la base de datos
	$.ajax({
		url : "/Rutina_app/",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
		cache : false,
	}).done(function (data, textStatus, jqXHR) {
		
		muestrausuario(data);
		
		
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}


/* Función que imprime un resumen de todos los datos de un usuario 
   en una tabla */
function muestrausuario(jsonUserArray) {
	
	// Iteramos para cada una de los usuarios e imprimimos sus campos
	for (var i = 0; i < jsonUserArray.length; i++) {
		var obj = jsonUserArray[i];


		var cookie = JSON.parse($.cookie('RutinaUsuario'));
		   if(obj.userId==cookie.userid){
			$('[name="user_email"]').val(obj.userId);
			$('[name="user_name"]').val(obj.userName);
			$('[name="user_birthdate"]').val(obj.userBirthDate);
			$('[name="user_phonenumber"]').val(obj.userPhoneNumber);
			}
			
		}
}
 
