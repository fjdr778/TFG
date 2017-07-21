/*
 * Controlador de la página userAdd.html
 * 
 * 
 * Diseño: Francisco José Díaz Romero
 * All rights reserved
 * Version 2.0.0
 *
 */

$(function () {
	// Inicializamos el plugin de validación
	$('#register_form').validate({
		// Establecemos las reglas de validación para
		// cada uno de los campos del formulario
		rules: {
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
			},
			terms: {
				required: true
			}
		},
		highlight: function (input) {
			console.log(input);
			$(input).parents('.form-line').addClass('error');
		},
		unhighlight: function (input) {
			$(input).parents('.form-line').removeClass('error');
		},
		errorPlacement: function (error, element) {
			$(element).parents('.input-group').append(error);
			$(element).parents('.form-group').append(error);
		},
		// Establecemos la función que se ejecutará en caso
		// de envío del formulario.
		submitHandler : function(form) {
			console.log("hola");
			sendUserData();

		}
	});
});

/* Evento que lanza el envío del formulario */
function submitForm() {

	var statSend = false;
	function checkSubmit() {
		if (!statSend) {
			statSend = true;
			$("#register_form").submit();
			return true;
		} else {
			alert("El formulario ya se esta enviando...");
			return false;
		}
	}

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

	// Añadimos el usuario a la base de datos
	$.ajax({
		url : "/RutinaRegister/",
		type : "POST",
		data : JSON.stringify(user_json),
		contentType : "application/json",
		headers: {'X-CSRF-TOKEN': cookie.csrf},
		timeout: 1000
	}).done(function(data, textStatus, jqXHR) {
		alert("Se ha dado de alta con éxito. Bienvenido a Rutina_app España.");
		window.location.href = "login.html";
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Ha habido un problema en el envío de sus datos.\n " +
		"Le recomendamos que lo intente de nuevo.");
		window.location.href = "UserAdd.html";
	});
}
