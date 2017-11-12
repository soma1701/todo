toDoApp.controller('trashController',function($scope, notesService, $location){
	var test = {};
	$scope.margin = 0;
	$scope.view = 'grid';
	$scope.notes = {};
	var httpGetNotes = notesService.getNotes("TRASH");

	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		if(response.status=='511')
			$location.path('/loginPage')
		console.log(response);
	});
	$scope.editNote = function(note){
		notesService.editNotes(note);
		modalInstance.close('resetModel');
		$scope.note = {};
	}
	$scope.deleteNote = function(id){
		var deleteNote = notesService.deleteNotes(id);
		deleteNote.then(function(response){
			httpGetNotes;
			
		});
	}
});