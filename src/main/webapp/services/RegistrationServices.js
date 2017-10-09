var toDoApp = angular.module('toDoApp');

toDoApp.factory('registrationServices', function($http) {
	var register = {};
	
	register.registerUser2 = function(user) {
		$http({
			method : "POST",
			url : 'register',
			data : user,
		}).then(function(response) {
			console.log(response.data.errorMessage);
		}, function(response) {
			console.log(response.data.errorMessage)
		});
	}
	
	return register;
});