var toDoApp = angular.module('toDoApp', ['ui.router']);

toDoApp.config(function($stateProvider, $urlRouterProvider) {
	
	$stateProvider
	.state('registrationpage', {
		url : '/registrationpage',
		templateUrl : 'template/registration.html'
	})
	.state('loginPage',{
		url: '/loginPage',
		templateUrl : 'template/login.html'
	})
	.state('homePage',{
		url :'/homePage',
		templateUrl :'template/homePage.html'
	})
	
	$urlRouterProvider.otherwise('loginPage');
});

