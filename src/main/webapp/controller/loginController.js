toDoApp.controller('loginController', function($scope, loginService,$location){
	
	$scope.loginUser = function(){
		loginService.loginUser($scope.user);
	}
	$scope.sendRedirect = function(){
		$location.path('/registrationpage')
	}
})