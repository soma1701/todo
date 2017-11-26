toDoApp.controller('noteDetailsController',function(notesService, $scope, $uibModal){
	
	var modalInstance;
	var collaboratorPopup;
	$scope.colors=[{
		"color":'#8e44ad',
		"path":'images/purple.png'
	},
	{
		"color":'#3498db',
		"path":'images/blue.png'
	},
	{
		"color":'#f1c40f',
		"path":'images/yellow.png'
	},
	{
		"color":'#1abc9c',
		"path":'images/green.png'
	},
	{
		"color":'#2ecc71',
		"path":'images/seegreen.png'
	},
	{
		"color":'#f1c40f',
		"path":'images/yellow.png'
	},
	{
		"color":'#f39c12',
		"path":'images/darkyellow.png'
	},
	{
		"color":'#c0392b',
		"path":'images/darkred.png'
	},
	{
		"color":'#ffffff',
		"path":'images/white.png'
	},
	{
		"color":'#000000',
		"path":'images/black.png'
	},
	{
		"color":'#34495e',
		"path":'images/darkgrey.png'
	},
	{
		"color":'#d35400',
		"path":'images/orange.png'
	},
	];
	
	$scope.editNotes = function(note){
		// note.image=note.imageSrc;
		console.log("note :::"+note);
		var editNote = notesService.editNotes(note);
		modalInstance.close('resetModel');
		$scope.note = {};
		editNote.then(function(response){
			$scope.note=response.data;
			getNotes();
		}),then(function(response){
			if(response.status=='400')
				$location.path('/loginPage')
				console.log(response);
		});
	}
	$scope.open = function (note) {
		$scope.note = note;
		modalInstance = $uibModal.open({
			templateUrl: 'template/new-note.html',
			scope : $scope
		});
	}
	$scope.openCollaborator = function(note){
		$scope.note = note;
		collaboratorPopup = $uibModal.open({
			templateUrl: 'template/collaborator.html',
			scope : $scope
		});
	};
	$scope.shareNote = function(note){
		var shareNote = notesService.shareNote(note, $scope.collaboratorEmail);
//		$scope.closeCollaborator;
	}
	$scope.closeCollaborator = function(){
		collaboratorPopup.close('resetModel');
	}
	$scope.makeCopy = function(note){
		var copyNote = notesService.saveNotes(note);
		modalInstance.close('resetModel');
		$scope.note = {};
		copyNote.then(function(response){
			getNotes();
		}),then(function(response){
			if(response.status=='400')
				$location.path('/loginPage')
				console.log(response);
		});
	}
	$scope.openAddLabel = function(note){
		$scope.note = note;
		$scope.addLabelModal = $uibModal.open({
			templateUrl: 'template/addLabelToNote.html',
			scope : $scope
			});
		$scope.addLabelModal.result.catch(function(){
			notesService.editNotes($scope.note);
		});
	}
	$scope.uploadImage = function(env,note){
		var obj = $(env.target).parent().find(".updateImage");
		obj.trigger("click");
	}
	$scope.updatePinup = function(note){
		// note.image=note.imageSrc;
		var updateImage = notesService.editNotes(note);
	}
	$scope.deleteNote = function(id){
		var deleteNote = notesService.deleteNotes(id);
		modalInstance.close('resetModel');
		deleteNote.then(function(response){
			httpGetNotes;
			
		});
	}
	$scope.datetimepicker=function(){
    	$('#datetimepicker6').datetimepicker();
    	var reminder = $('#datetimepicker6').val();
    	console.log(reminder);
    }
    $scope.tet = function(note){
    	var reminder = $('#datetimepicker6').val();
    	note.reminder = reminder;
    	notesService.editNotes(note);
    }
});