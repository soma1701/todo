/*var todoApp = angular.module('toDoApp');*/
toDoApp.controller('notesController', function($scope, saveNotesService) {
	var addNote={};
	$scope.saveNotes = function() {
		addNote.title=document.getElementById("note-title").innerHTML;
		addNote.description=document.getElementById("note-description").innerHTML;
		console.log(addNote);
		saveNotesService.saveNotes(addNote);
	}
	var httpGetNotes = saveNotesService.getNotes();

	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		console.log(response);
	});

})