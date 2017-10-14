var todoApp = angular.module('toDoApp');
toDoApp.controller('notesController',function($scope,noteService){
	$scope.note = {};
	$scope.saveNotes = function(){
		noteServie.saveNotes($scope.note);
	}
})