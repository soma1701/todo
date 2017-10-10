var toDoApp = angular.module('toDoApp');

toDoApp.factory('loginService', function($http){
	
	var login = {};
	
	login.loginUser = function(user) {
		$http({
			method : "POST",
			url : 'login',
			data : user,
		}).then(function(response) {
			console.log(response.data.errorMessage);
		}, function(response) {
			console.log(response.data.errorMessage)
		});
	}
	
	return login;
	
});