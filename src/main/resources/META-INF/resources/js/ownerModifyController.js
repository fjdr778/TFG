
/*
 * Controlador de la página ownerModify.html
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Funciones a ejecutar en la carga de la página */
$(document).ready(function() {
	// Obtenemos los datos del local de la base de datos
	getOwnerData();
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
			sendOwnerData();
		}
	});

});


/* Evento que lanza el envío del formulario */
function submitForm() {
	$("#personal_form").submit();
}


/* Función de extracción y envío de los datos del formulario */
function sendOwnerData() {

	// Obtenemos los datos del propietario del formulario
	var owner_Id = $('[name="user_email"]').val();
	var owner_Name = $('[name="user_name"]').val();
	var owner_BirthDate = $('[name="user_birthdate"]').val();
	var owner_PhoneNumber = $('[name="user_phonenumber"]').val();
	var owner_Passw = $('[name="user_password"]').val();
	
	// JSON formado con los datos extraídos del formulario
	var owner_json = {
		ownerId : owner_Id,
		ownerName : owner_Name,
		ownerBirthDate : owner_BirthDate,
		ownerPhoneNumber: owner_PhoneNumber,
		ownerPassw : owner_Passw
	};
	
	// Obtenemos la cookie
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	// Añadimos la información del propietario a la BBDD
	$.ajax({
		url : "/Rutina_app/" + cookie.userid,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "POST",
		data : JSON.stringify(owner_json),
		contentType : "application/json",
		timeout: 1000
	}).done(function(data, textStatus, jqXHR) {
		// Reinicializamos el campo userid de la cookie, por si
		// el usuario lo ha modificado
		cookie.userid = owner_Id;
		// Informamos de la operación y redirigimos
		alert("Modificación realizada con éxito");
		window.location.href = "index.html";
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});	
}


function getOwnerData() {
	
	// Obtenemos la cookie de usuario
	var cookie = JSON.parse($.cookie('RutinaUsuario'));
	
	// Obtenemos los datos del propietario de la BBDD
	$.ajax({
		url : "/Rutina_app/" + cookie.userid,
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		type : "GET",
		dataType : "json",
	// Imprimimos los datos del propietario en el modelo
	// No imprimimos la contraseña
	}).done(function (data, textStatus, jqXHR) {
		$('[name="user_email"]').val(data[0].ownerId);
		$('[name="user_name"]').val(data[0].ownerName);
		$('[name="user_birthdate"]').val(data[0].ownerBirthDate);
		$('[name="user_phonenumber"]').val(data[0].ownerPhoneNumber);
	// Avisamos al usuario de que ha surgido un error
	}).fail(function (jqXHR, textStatus, errorThrown) {
		alert("Se ha producido un error.");
	});
}
