var toDoApp = angular.module('toDoApp', ['ui.router','ui.bootstrap']);

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
	.state('dummyLogin',{
		url :'/dummyLogin',
		templateUrl:'template/dummyLogin.html',
		controller:'googleController'
	})
	.state('dummyFbLogin',{
		url :'/dummyFbLogin',
		templateUrl:'template/dummyFbLogin.html',
		controller:'fbController'
	})
	
	$urlRouterProvider.otherwise('loginPage');
});

