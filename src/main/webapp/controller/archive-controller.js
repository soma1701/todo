toDoApp.controller('archiveController',function($scope, notesService){
	var test = {};
	$scope.margin = 0;
	$scope.view = 'grid';
	$scope.notes = {};
	var httpGetNotes = notesService.getNotes("ARCHIVE");

	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		if(response.status=='511')
			$location.path('/loginPage')
		console.log(response);
	});
	$scope.editNotes = function(note){
		notesService.editNotes(note);
		modalInstance.close('resetModel');
		$scope.note = {};
	}
	$scope.makeCopy = function(note){
		notesService.saveNotes(note);
	}
});