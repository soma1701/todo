toDoApp.controller('googleController', function($http, $location) {
	$http({
		method : "GET",
		url : 'tokenAftergLogin'
	}).then(function(response) {
		console.log("google resp")
		console.log(response.data.responseMessage);
		localStorage.setItem('accessToken', response.data.responseMessage);
		$location.path('homePage/notes');
	}, function(response) {
		console.log(response.data.responseMessage);
	});
});	