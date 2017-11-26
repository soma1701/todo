toDoApp.controller('labelsController',function($scope, $location,labelService){
	
	$scope.stateLabel ={
			isArchived:false,
			isTrashed:false,
			isEditable:false
	}
	var path = $location.path();
	var labelName = path.substr(path.lastIndexOf("/")+1);
	
//	function getLabels(){
		var httpGetNotes = labelService.getLabelNotes(labelName);
		httpGetNotes.then(function(response) {
			$scope.notes = response.data;
		}, function(response) {
			if(response.status=='400')
				$location.path('/loginPage')
				console.log(response);
		});
//	}
	
	
	
});

