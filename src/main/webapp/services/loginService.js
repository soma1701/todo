//var toDoApp = angular.module('toDoApp');

toDoApp.factory('loginService', function($http, $location){
	
	var login = {};
	
	login.loginUser = function(user) {
		$http({
			method : "POST",
			url : 'login',
			data : user,
		}).then(function(response) {
			console.log(response.data.responseMessage)
			$location.path('homePage')
		}, function(response) {
			console.log(response.data.responseMessage)
		});
	}
	
	return login;
	
});