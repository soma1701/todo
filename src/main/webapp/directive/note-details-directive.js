toDoApp.directive('noteDetails',function(){
	return {
		restrict:'E',
		scope: {
			state:'=',
			notes:'='
		},
		templateUrl:'template/note-details.html',
		controller:'noteDetailsController',
	}
});