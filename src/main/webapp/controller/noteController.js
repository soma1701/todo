toDoApp.controller('notesController', function($scope, fileReader,toastr,notesService,$location, $uibModal,$interval, dataStore, $rootScope,
		$filter,labelService,$interval) {
	
	getNotes();
//	$scope.view = '';
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
        localStorage.setItem("view",$scope.view);
	});
	$scope.$on('searchText-change', function(event, data){
        $scope.searchText = dataStore.getSearchText();
	});
	function getNotes(){
		var httpGetNotes = notesService.getNotes('ALL');
		httpGetNotes.then(function(response) {
			$scope.notes = response.data;
			/*$interval(function(){
				var i=0;
				for(i;i<$scope.notes.length;i++){
				if($scope.notes[i].reminderStatus!='false'){
				console.log($scope.notes[i].reminderStatus);
				var currentDate=$filter('date')(new Date(),'MM/dd/yyyy h:mm a');
				console.log(currentDate);
				if($scope.notes[i].reminderStatus === currentDate){

				toastr.success('You have a reminder for a note', 'check it out');
				}
				}
				}
				},9000);*/
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
//		toastr.success('success', 'successfully added');
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
