toDoApp.controller('notesController', function($scope, notesService,$location, $uibModal, dataStore, $rootScope) {
	var addNote={};
	var editNote={};
	$scope.note = {};
	$scope.note.description = '';
	$scope.note.title = '';
	var modalInstance;
	
	$scope.open = function (note) {
		$scope.note = note;
		modalInstance = $uibModal.open({
		templateUrl: 'template/new-note.html',
		scope : $scope
		});
		};
		$scope.$on('toggleSideBar-change', function(event, data){
//	           $scope.width = dataStore.getWidth();
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
	var httpGetNotes = notesService.getNotes('ALL');

	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		if(response.status=='511')
			$location.path('/loginPage')
		console.log(response);
	});
	$scope.deleteNotes = function(id){
		console.log("notes id"+id);
		var deleteNote = notesService.deleteNotes(id);
		deleteNote.then(function(response){
			httpGetNotes;
			
		});
	}
	$scope.editNotes = function(note){
		notesService.editNotes(note);
		modalInstance.close('resetModel');
		$scope.note = {};
	}
	$scope.makeCopy = function(note){
		notesService.saveNotes(note);
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


/*toDoApp.controller('ModalInstanceCtrl', function ($scope, $modalInstance, user) {
	  $scope.NOTE = user;
	  $scope.ok = function () {
	    $modalInstance.close();
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	});
*/