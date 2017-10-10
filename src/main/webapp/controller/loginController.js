var toDoApp = angular.module('toDoApp');
toDoApp.controller('loginController', function($scope, $http, loginService,$location){
	$scope.loginUser = function(){
		loginService.loginUser($scope.user);
	}
	$scope.sendRedirect = function(){
		location.path('/registration.html')
	}
})