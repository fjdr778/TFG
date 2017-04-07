
/*
 * Controlador de la página ownerAdd.html
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
	$('#register_form').validate({
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
	$("#register_form").submit();
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
	var cookie = JSON.parse($.cookie('Rutinaappusers'));
	
	// Añadimos el evento a la base de datos
	$.ajax({
		url : "/RutinaRegister/",
		type : "POST",
		data : JSON.stringify(owner_json),
		contentType : "application/json",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		timeout: 1000
	}).done(function(data, textStatus, jqXHR) {
		alert("Se ha dado de alta con éxito. Bienvenido a Rutina_app España.");
		window.location.href = "login.html";
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Ha habido un problema en el envío de sus datos.\n " +
				"Le recomendamos que lo intente de nuevo.");
		window.location.href = "ownerAdd.html";
	});
}
