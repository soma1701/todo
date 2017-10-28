var toDoApp = angular.module('toDoApp');
toDoApp.controller('notesController', function($scope, saveNotesService,$location) {
	var addNote={};
	$scope.actualInput = false;
	
	$scope.saveNotes = function() {
		addNote.title=document.getElementById("note-title-input").innerHTML;
		addNote.description=document.getElementById("note-description-input").innerHTML;
		console.log(addNote);
		saveNotesService.saveNotes(addNote);
	}
	var httpGetNotes = saveNotesService.getNotes();

	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		if(response.status=='511')
			$location.path('/loginPage')
		console.log(response);
	});
	

})