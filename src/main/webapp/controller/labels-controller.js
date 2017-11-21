toDoApp.controller('labelsController',function($scope, $location,labelService){
	var path = $location.path();
	var labelName = path.substr(path.lastIndexOf("/")+1);
	var httpGetLabels = labelService.getLabels();
	
	httpGetLabels.then(function(response) {
		$scope.labels = response.data;
	}, function(response) {
		if(response.status=='400')
			$location.path('/loginPage')
			console.log(response);
	});
	alert(labelName);
});

