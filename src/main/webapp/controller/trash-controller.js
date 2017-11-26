toDoApp.controller('trashController',function($scope, notesService,$uibModal, $location, dataStore, $rootScope){
	var test = {};
	$scope.margin = 0;
	$scope.view = 'grid';
	$scope.notes = {};
	$scope.stateTrashed ={
	    	isPinned:false,
	    	isArchived:false,
	    	isTrashed:true
	    }
	var modalInstance;
	var httpGetNotes = notesService.getNotes("TRASH");
	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		if(response.status=='511')
			$location.path('/loginPage')
			console.log(response);
	});
	
	$scope.open = function (note) {
		$scope.note = note;
		modalInstance = $uibModal.open({
		templateUrl: 'template/new-note.html',
		scope : $scope
		});
		};
		$scope.$on('toggleSideBar-change', function(event, data){
	           $scope.margin = dataStore.getMargin();
	     });
		$scope.$on('view-change', function(event, data){
	           $scope.view = dataStore.getView();
	     });
		$scope.view = 'grid';
		$scope.showNewNote = false;
		$scope.tabClicked = function(){
			$scope.showNewNote = true;
		};
	
	$scope.notesTab = true;	
	$scope.req = [
		
		{
				background:"rgb(99, 99, 99)",
				bordercolor:"#fb0",
				color:"black"
		},
		{
			background:"rgb(99, 99, 99)",
			bordercolor:"#fb0",
			color:"black"
	}];

	$scope.editNote = function(note){
		notesService.editNotes(note);
		modalInstance.close('resetModel');
		$scope.note = {};
	}
	$scope.deleteNote = function(id){
		var deleteNote = notesService.deleteNotes(id);
		modalInstance.close('resetModel');
		deleteNote.then(function(response){
			httpGetNotes;
			
		});
	}
});

