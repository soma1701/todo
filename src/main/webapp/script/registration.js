var toDoApp = angular.module('toDoApp', ['ui.router']);

toDoApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state('registrationpage', {
		url : '/registrationpage',
		templateUrl : 'template/registration.html'
	});

	$urlRouterProvider.otherwise('registrationpage');
});