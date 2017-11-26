toDoApp.controller('archiveController',function($scope, fileReader,notesService,$location, $uibModal, dataStore, $rootScope, labelService){

	$scope.stateNote ={
			isArchived:true,
			isTrashed:false,
			isEditable:false
	}
	/*var test = {};
	$scope.margin = 0;
	$scope.view = 'grid';
	$scope.notes = {};
	
	
	var httpGetNotes = notesService.getNotes("ARCHIVE");
	httpGetNotes.then(function(response) {
		console.log(response.data);
		$scope.notes = response.data;
	}, function(response) {
		if(response.status=='400')
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
*/
	

		
		$scope.imageSrc = "";
		    
		    $scope.$on("fileProgress", function(e, progress) {
		      $scope.progress = progress.loaded / progress.total;
		    });
		    $scope.uploadImage = function(){
		    	$('#imgUpload').trigger('click');
		    }
			var addNote={};
			var editNote={};
			$scope.note = {};
			$scope.note.description = '';
			$scope.note.title = '';
			$scope.note.imageSrc = '';
			var modalInstance;
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
			getNotes();
			getLabels();
			$scope.open = function (note) {
				$scope.note = note;
				modalInstance = $uibModal.open({
				templateUrl: 'template/new-note.html',
				scope : $scope
				});
				};
			$scope.openCollaborator = function(){
				modalInstance = $uibModal.open({
					templateUrl: 'template/collaborator.html',
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
						background:"#fb0",
						bordercolor:"#fb0",
						color:"black"
				}];
			$scope.saveNotes = function() {
				addNote.title=$scope.note.title;
				addNote.description=$scope.note.description;
				addNote.color=$scope.note.color;
				addNote.isArchived=$scope.note.isArchived;
				addNote.image=$scope.note.imageSrc;
				notesService.saveNotes(addNote);
				$scope.showNewNote = false;
				$scope.note.description='';
				getNotes();
			}
			function getNotes(){
				var httpGetNotes = notesService.getNotes("ARCHIVE");
				httpGetNotes.then(function(response) {
					console.log(response.data);
					$scope.notes = response.data;
				}, function(response) {
					if(response.status=='400')
						$location.path('/loginPage')
					console.log(response);
				});
			}
			function getLabels(){
				var httpGetLabels = labelService.getLabels();
				
				httpGetLabels.then(function(response) {
					$scope.labels = response.data;
				}, function(response) {
					if(response.status=='400')
						$location.path('/loginPage')
						console.log(response);
				});
			}
			$scope.deleteNotes = function(id){
				console.log("notes id"+id);
				var deleteNote = notesService.deleteNotes(id);
				modalInstance.close('resetModel');
				deleteNote.then(function(response){
					getNotes();
				}),then(function(response){
					if(response.status=='400')
						$location.path('/loginPage')
						console.log(response);
				});
			}
			$scope.editNotes = function(note){
				//note.image=note.imageSrc;
				var editNote = notesService.editNotes(note);
				modalInstance.close('resetModel');
				$scope.note = {};
				editNote.then(function(response){
					getNotes();
					$scope.note=response.data;
				}),then(function(response){
					if(response.status=='400')
						$location.path('/loginPage')
						console.log(response);
				});
			}
			$scope.makeCopy = function(note){
				var copyNote = notesService.saveNotes(note);
				modalInstance.close('resetModel');
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
				note.image=note.imageSrc;
				var updateImage = notesService.editNotes(note);
			}
});
			
