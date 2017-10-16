var todoApp = angular.module('toDoApp');
toDoApp.controller('notesController', function($scope, saveNotesService) {

	

	// $scope.note = {};
	$scope.saveNotes = function() {
		saveNotesService.saveNotes($scope.note);
	}
	var httpGetNotes = saveNotesService.getNotes();

	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		console.log(response);
	});

})