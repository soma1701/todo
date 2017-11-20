toDoApp.factory('notesService',function($http,$location){
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
			if(response.status=='400')
				$location.path('/loginPage')
			console.log("error" +response.data.myResponseMessage);
		});
	}
	note.getNotes = function(actionType) {
		var actionUrl;
		if(actionType === 'ALL'){
			actionUrl = 'notesCredential/getNotes';
		}else if(actionType === 'ARCHIVE'){
			actionUrl = 'notesCredential/getArchivedNotes';
		}else if(actionType === 'TRASH'){
			actionUrl = 'notesCredential/getTrashNotes';
		}else if(actionType === 'LABELS'){
			actionUrl = 'notesCredential/getLabels';
		}
		return $http({
			method:"GET",
			headers:{
				'accessToken' : localStorage.getItem("accessToken")
			},
			url: actionUrl
		})/*.then(function(response){
			console.log("response message" +response.data);
		},function(response){
			if(response.status=='400')
				$location.path('/loginPage')
			console.log("error" +response.data.myResponseMessage);
		});*/
	}
	note.deleteNotes = function(id){
		console.log("inside delete function;-");
		return $http({
			method:"DELETE",
			url:'notesCredential/deleteNotes/'+id,
			headers:{
				'accesstoken':localStorage.getItem("accessToken")
			}
		}).then(function(response){
			console.log("response message" +response.data);
		},function(response){
			if(response.status=='400')
				$location.path('/loginPage')
			console.log("error" +response.data.myResponseMessage);
		});
	}
	note.editNotes = function(notes){
			console.log("inside edit notes service:-");
			console.log(notes);
			return $http({
				method:"POST",
				url:'notesCredential/editNotes',
				headers:{
					'accessToken':localStorage.getItem("accessToken")
				},
				data:notes,
			}).then(function(response){
				console.log("response message" +response.data);
			},function(response){
				if(response.status=='400')
					$location.path('/loginPage')
				console.log("error" +response.data.responseMessage);
			});
		}
	return note;
});
