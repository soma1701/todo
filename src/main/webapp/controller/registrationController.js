var toDoApp = angular.module('toDoApp');

toDoApp.controller('registrationControllers', function($scope, registrationServices, $location) {
	$scope.user = {};

	 $scope.registerUser = function() {
		
		registrationServices.registerUser2($scope.user);
		
	}
	 $scope.redirectToSignIn = function() {
			$location.path('/loginPage');
		}

});