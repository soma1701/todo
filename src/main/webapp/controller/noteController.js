toDoApp.controller('notesController', function($scope, fileReader,notesService,$location, $uibModal, dataStore, $rootScope, labelService) {
	
	getNotes();
	$scope.view = 'grid';
	$scope.stateNote ={
			isArchived:false,
			isTrashed:false,
			isEditable:false
	}
	$scope.$on('toggleSideBar-change', function(event, data){
        $scope.margin = dataStore.getMargin();
	});
	$scope.$on('view-change', function(event, data){
        $scope.view = dataStore.getView();
	});
	function getNotes(){
		var httpGetNotes = notesService.getNotes('ALL');
		httpGetNotes.then(function(response) {
			$scope.notes = response.data;
		}, function(response) {
			if(response.status=='400')
				$location.path('/loginPage')
				console.log(response);
		});
	}
	
	
	
	
	
	
	
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
toDoApp.directive('focus',
		function($timeout) {
			 return {
			 scope : {
			   trigger : '@focus'
			 },
			 link : function(scope, element) {
			  scope.$watch('trigger', function(value) {
			    if (value === "true") {
			      $timeout(function() {
			       element[0].focus();
			      });
			   }
			 });
			 }
			};
	}); 
