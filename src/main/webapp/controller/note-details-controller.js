toDoApp.controller('noteDetailsController',function(notesService, $scope, $uibModal, $location, labelService, dataStore){
	
	var modalInstance;
	var collaboratorPopup;
	var path = $location.path();
	var labelName = path.substr(path.lastIndexOf("/")+1);
	var httpGetLabels = labelService.getLabels(labelName);
	$scope.view = 'grid';
	httpGetLabels.then(function(response) {
		$scope.labels = response.data;
	}, function(response) {
		if(response.status=='400')
			$location.path('/loginPage')
			console.log(response);
	});
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
//		$scope.note = {};
		editNote.then(function(response){
//			$scope.note=response.data;
//			getNotes();
		}),then(function(response){
			if(response.status=='400')
				$location.path('/loginPage')
				console.log(response);
		});
	}
	$scope.fbShare = function(note) {
		FB.init({
			appId : '129892814380911',
			status : true,
			cookie : true,
			xfbml : true,
			version : 'v2.4'
		});

		FB.ui({
			method : 'share_open_graph',
			action_type : 'og.likes',
			action_properties : JSON.stringify({
				object : {
					'og:title' : note.title,
					'og:description' :note.description
				}
			})
		}, function(response) {
			if (response && !response.error_message) {
				alert('Posting completed.');
			} else {
				alert('Error while posting.');
			}
		});
	};
	$scope.open = function (note, state) {
		$scope.testState = state;
		$scope.note = note;
		$scope.testState.isEditable = true;
		modalInstance = $uibModal.open({
			templateUrl: 'template/new-note.html',
			scope : $scope
		});
		modalInstance.result.catch(function(){
			notesService.editNotes($scope.note);
			$scope.testState.isEditable = false;
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
    $scope.saveReminder = function(note){
    	var reminder = new Date($('#datetimepicker6').val());
    	note.reminder = reminder;
    	notesService.editNotes(note);
    }
    $scope.$on("fileProgress", function(e, progress) {
        $scope.progress = progress.loaded / progress.total;
	});
    $scope.removeLabel = function (note, item) {
    	$scope.note = note;
    	var comparator = angular.equals;
        if (angular.isArray($scope.note.labels)) {
          for (var i = $scope.note.labels.length; i--;) {
            if (comparator($scope.note.labels[i], item)) {
            	$scope.note.labels.splice(i, 1);
              break;
            }
          }
        }
      notesService.editNotes(note);
    }
    $scope.removeUser = function (note, item) {
    	$scope.note = note;
    	var comparator = angular.equals;
        if (angular.isArray($scope.note.user)) {
          for (var i = $scope.note.user.length; i--;) {
            if (comparator($scope.note.user[i].email, item)) {
            	$scope.note.user.splice(i, 1);
              break;
            }
          }
        }
        notesService.editNotes(note);
      }
    $scope.$on('searchText-change', function(event, data){
        $scope.searchText = dataStore.getSearchText();
	});
    $scope.$on('toggleSideBar-change', function(event, data){
        $scope.margin = dataStore.getMargin();
	});
	$scope.$on('view-change', function(event, data){
        $scope.view = dataStore.getView();
	});
});