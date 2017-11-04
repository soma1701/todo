var toDoApp = angular.module('toDoApp');
toDoApp.directive("cardsInput",function(){
	return{
		scope:false,
		templateUrl:"template/cards-input.html"
	}
})