var toDoApp = angular.module('toDoApp');

toDoApp.factory('saveNotesService',function($http){
	var note ={};
	
	note.saveNotes = function(notes){
		console.log(notes);
		$http({
			method:"POST",
			url:'notesCredential/saveNotes',
			headers:{
				'accessToken' : localStorage.getItem("accessToken")
			},
			data: notes,		
						
		}).then(function(response){
			console.log(response.data.responseMessage);
		},function(response){
			console.log(response.data.responseMessage);
		});
	}
	note.getNotes = function() {
		return $http({
			method:"GET",
			headers:{
				'accessToken' : localStorage.getItem("accessToken")
			},
			url: 'notesCredential/getNotes'
		})
	}
	return note;
});
