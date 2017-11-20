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
		},function(response){
			if(response.status=='400')
				$location.path('/loginPage');
		});
	}
	label.getLabels = function() {
		return $http({
			method:"GET",
			url: 'LabelsCredential/getLabels',
			headers:{
				'accessToken' : localStorage.getItem("accessToken")
			},
			
		}).then(function(response){
		},function(response){
			if(response.status=='400')
				$location.path('/loginPage');
		});
	}
	label.deleteLabel = function(id){
		return $http({
			method:"DELETE",
			url:'LabelsCredential/deleteLabels/'+id,
			headers:{
				'accesstoken':localStorage.getItem("accessToken")
			}
		})
	}
	label.editNotes = function(label){
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