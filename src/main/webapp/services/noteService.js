//var toDoApp = angular.module('toDoApp');

toDoApp.factory('saveNotesService',function($http,$location){
	var note ={};
	
	note.saveNotes = function(notes){
		console.log("inside notes service");
		return $http({
			method:"POST",
			url:'notesCredential/saveNotes',
			headers:{
				'accessToken' : localStorage.getItem("accessToken")
			},
			data: notes,		
						
		}).then(function(response){
			console.log("response message" +response.data);
		},function(response){
			console.log("error" +response.data.myResponseMessage);
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
