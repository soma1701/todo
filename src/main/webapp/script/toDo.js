var toDoApp = angular.module('toDoApp', ['ui.router']);

toDoApp.config(function($stateProvider, $urlRouterProvider) {
	
	$stateProvider
	.state('registrationpage', {
		url : '/registrationpage',
		templateUrl : 'template/registration.html'
	})
	.state('loginPage',{
		url: '/loginpage',
		templateUrl : 'template/login.html'
	});

	$urlRouterProvider.otherwise('registrationpage');
});

