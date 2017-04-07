
/*
 * Librería de manejo de mapas para la localización de locales en el lado del
 * cliente, con el fin de facilitar dicha tarea al usuario.
 * 
 * 
 * Diseño por Adrián Gil Gago
 * Todos los derechos reservados.
 * Versión: 1.0
 *
 */


/* Función que realiza la inicialización del mapa */
function initMap() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 15,
		center : {
			lat : 37.389092,
			lng : -5.984459
		}
	});

	var geocoder = new google.maps.Geocoder();
	var defaultLatlng = new google.maps.LatLng(37.389092, -5.984459);
	var marker = new google.maps.Marker({
		map : map,
		position : defaultLatlng
	});

	document.getElementById('submit').addEventListener('click', function() {
		geocodeAddress(geocoder, map, showResult, marker);
	});

}


/* Función que imprime los datos de latitud y longitud */
function showResult(result) {
	document.getElementById('latitude').value = result.geometry.location.lat();
	document.getElementById('longitude').value = result.geometry.location.lng();
}


/* Función que geocodifica la dirección que se le pasa como parámetro */
function geocodeAddress(geocoder, resultsMap, callback, marker) {
	var address = document.getElementById('address').value
			+ ", Sevilla, España";

	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status === google.maps.GeocoderStatus.OK) {
			callback(results[0]);
			resultsMap.setCenter(results[0].geometry.location);
			marker.setPosition(results[0].geometry.location);
		} else {
			alert('Ha habido un fallo durante la geocodificación: ' + status);
		}
	});
}