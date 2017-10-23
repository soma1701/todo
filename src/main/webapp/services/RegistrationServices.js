//var toDoApp = angular.module('toDoApp');

toDoApp.factory('registrationServices', function($http,$location) {
	var register = {};
	
	register.registerUser2 = function(user) {
		$http({
			method : "POST",
			url : 'register',
			data : user,
		}).then(function(response) {
			console.log(response.data.responseMessage);
			$location.path('loginPage')
		}, function(response) {
			console.log(response.data.responseMessage);
		});
	}
	return register;
});