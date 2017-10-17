/*var toDoApp = angular.module('toDoApp');*/

toDoApp.factory('saveNotesService',function($http){
	var note ={};
	
	note.saveNotes = function(notes){
		console.log(notes);
		$http({
			method:"POST",
			url:'saveNotes',
			data: notes,
			
		}).then(function(response){
			console.log(response.data.errorMessage);
		},function(response){
			console.log(response.data.errorMessage);
		});
	}
	note.getNotes = function() {
		return $http({
			method:"GET",
			url: 'getNotes'
		})
	}
	return note;
});