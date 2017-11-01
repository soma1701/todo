toDoApp.controller('fbController', function($http, $location) {
	$http({
		method : "GET",
		url : 'tokenAftergFbLogin'
	}).then(function(response) {
		console.log(response.data.responseMessage);
		localStorage.setItem('accessToken', response.data.responseMessage);
		$location.path('homePage');
	}, function(response) {
		console.log(response.data.responseMessage);
	});
});	