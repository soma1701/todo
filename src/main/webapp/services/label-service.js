toDoApp.factory('labelService',function($http,$location){
	var label ={};
	
	label.saveLabel = function(label){
		return $http({
			method:"POST",
			url:'LabelsCredential/saveLabel',
			headers:{
				'accessToken' : localStorage.getItem("accessToken")
			},
			data: label,		
						
		}).then(function(response){
			console.log("response message" +response.data);
		},function(response){
			console.log("error" +response.data.myResponseMessage);
		});
	}
	label.getLabels = function(actionType) {
		var actionUrl;
		if(actionType === 'ALL'){
			actionUrl = 'LabelsCredential/getNotes';
		}else if(actionType === 'ARCHIVE'){
			actionUrl = 'LabelsCredential/getArchivedNotes';
		}else if(actionType === 'TRASH'){
			actionUrl = 'LabelsCredential/getTrashNotes';
		}else if(actionType === 'LABELS'){
			actionUrl = 'LabelsCredential/getLabels';
		}
		return test;/*$http({
			method:"GET",
			headers:{
				'accessToken' : localStorage.getItem("accessToken")
			},
			url: actionUrl
		})*/
		
	}
	var test = [{
		text:'sourav'
	},
	{
		text:'soma'
	},
	{
		text:'shubhu'
	},
	{
		text:'Vanshu'
	}
	];
	label.deleteLabel = function(id){
		console.log("inside delete function;-");
		return $http({
			method:"DELETE",
			url:'labelCredential/deleteLabels/'+id,
			headers:{
				'accesstoken':localStorage.getItem("accessToken")
			}
		})
	}
	label.editNotes = function(label){
			console.log("inside edit notes service:-");
			console.log(label);
			return $http({
				method:"POST",
				url:'labelCredential/editLabels',
				headers:{
					'accessToken':localStorage.getItem("accessToken")
				},
				data:notes,
			}).then(function(response){
				console.log("edited notes:-");
				console.log(response.data);
			},function(response){
				console.log("error"+response.data.myResponseMessage);
				
			});
		}
		
	return label;
});
