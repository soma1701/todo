var toDoApp = angular.module('toDoApp');

toDoApp.controller('registrationControllers', function($scope, $http, registrationServices) {
	$scope.user = {};

	 $scope.registerUser = function() {
		
		registrationServices.registerUser2($scope.user);
		
	}

});