var toDoApp = angular.module('toDoApp');
toDoApp.directive("sideBar",function(){
	
	return{
		restrict:'E',
		templateUrl:'template/sidebar.html'
	};
});