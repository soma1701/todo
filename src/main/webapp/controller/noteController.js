var toDoApp = angular.module('toDoApp');
toDoApp.controller('notesController', function($scope, saveNotesService,$location) {
	var addNote={};
	$scope.actualInput = false;
	
	$scope.open = function () {
		console.log('opening pop up');
		var modalInstance = $uibModal.open({
		templateUrl: 'template/new-note.template.html'
		});
		};
		$scope.width = 0;
		$scope.margin = 0;
		$scope.openSideBar = false;
		$scope.toggleSideBar = function(){
			$scope.openSideBar = !$scope.openSideBar;
			if($scope.openSideBar){
				$scope.width = 250;
				$scope.margin = 250;
			}else{
				$scope.width = 0;
				$scope.margin = 0;
			}
		};
		$scope.notesData = '';
		$scope.showNewNote = false;
		$scope.tabClicked = function(){
			console.log($scope.showNewNote);
			$scope.showNewNote = true;
			console.log($scope.showNewNote);
		};
	
	$scope.notesTab = true;	
	$scope.req = [
		
		{
				background:"#fb0",
				bordercolor:"#fb0",
				color:"black"
		}];
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
	

});
toDoApp.directive('focus',
		function($timeout) {
	console.log("test");
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
