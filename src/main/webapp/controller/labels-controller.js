toDoApp.controller('labelsController',function($scope, $location){
	var path = $location.path();
	var labelName = path.substr(path.lastIndexOf("/")+1);
	alert(labelName);
});

