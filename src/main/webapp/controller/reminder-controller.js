toDoApp.controller('reminderController', function($scope, fileReader,notesService,$location, $uibModal, dataStore, $rootScope, labelService) {
	
	$scope.view = 'grid';
	$scope.statePinnedNote ={
			isPinned:true,
			isArchived:false,
			isTrashed:false,
			isEditable:false,
			isLabel:false
	}
	$scope.stateUnPinnedNote ={
			isPinned:false,
			isArchived:false,
			isTrashed:false,
			isEditable:false,
			isLabel:false
	}
	$scope.$on('toggleSideBar-change', function(event, data){
        $scope.margin = dataStore.getMargin();
	});
	$scope.$on('view-change', function(event, data){
        $scope.view = dataStore.getView();
	});
	$scope.$on('searchText-change', function(event, data){
        $scope.searchText = dataStore.getSearchText();
	});
		var httpGetNotes = notesService.getNotes('REMINDER');
		httpGetNotes.then(function(response) {
			$scope.notes = response.data;
		}, function(response) {
			if(response.status=='400')
				$location.path('/loginPage')
				console.log(response);
		});
	
	
	
	
	
	
	
	$scope.imageSrc = "";
    
	var addNote={};
	$scope.note = {
			description : '',
			title : '',
			imageSrc : ''
	};
		$scope.showNewNote = false;
		$scope.tabClicked = function(){
			$scope.showNewNote = true;
		};
	
	$scope.notesTab = true;	
	
	
	$scope.saveNotes = function() {
		addNote.title=$scope.note.title;
		addNote.description=$scope.note.description;
		addNote.color=$scope.note.color;
		addNote.isArchived=$scope.note.isArchived;
		addNote.image=$scope.note.image;
//		addNote.reminder = reminder;
		var saveNotes = notesService.saveNotes(addNote);
		$scope.showNewNote = false;
		$scope.note.description='';
		saveNotes.then(function(response){
			getNotes();
		},function(response){
			if(response.status=='400')
				$location.path('/loginPage')
			console.log("error" +response.data.myResponseMessage);
		});
		
	}
	
});
