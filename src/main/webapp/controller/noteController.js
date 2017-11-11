//var toDoApp = angular.module('toDoApp');
toDoApp.controller('notesController', function($scope, notesService,$location, $uibModal) {
	var addNote={};
	//$scope.actualInput = false;
	var editNote={};
	$scope.note = {};
	$scope.note.description = '';
	$scope.note.title = '';
	
	$scope.open = function (note) {
		$scope.note = note;
		var modalInstance = $uibModal.open({
		templateUrl: 'template/new-note.html',
		/*controller: 'ModalInstanceCtrl',
	      resolve: {
	        note: function () {
	          return $scope.note;
	        }
	      }*/
		scope : $scope
		});
		};
		$scope.width = 0;
		$scope.margin = 0;
		$scope.view = 'grid';
		$scope.isGridView = true;
		$scope.switchView = function(){
			if($scope.isGridView){
				$scope.view = 'list';
				$scope.isGridView = false;
			}else{
				$scope.view = 'grid';
				$scope.isGridView = true;
			}
		}
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
//		$scope.notesData = '';
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
		addNote.title=$scope.note.title;
		console.log("title" +$scope.note.title);
		addNote.description=$scope.note.description;
		console.log("description" +$scope.note.description);
		notesService.saveNotes(addNote);
		$scope.showNewNote = false;
		$scope.note.description='';
	}
	var httpGetNotes = notesService.getNotes();

	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		if(response.status=='511')
			$location.path('/loginPage')
		console.log(response);
	});
	$scope.deleteNote = function(id){
		console.log("notes id"+id);
		var deleteNote = notesService.deleteNote(id);
		deleteNote.then(function(response){
			httpGetNotes;
			
		});
	}
	$scope.editNotes = function(){
		editNote.title=$scope.note.title;
		editNote.description = $scope.note.description;
		editNote.notesId = $scope.note.notesId;
		notesService.editNotes(editNote);
	}
});
toDoApp.directive('focus',
		function($timeout) {
	//alert("test");
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


toDoApp.controller('ModalInstanceCtrl', function ($scope, $modalInstance, user) {
	  $scope.NOTE = user;
	  $scope.ok = function () {
	    $modalInstance.close();
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	});
