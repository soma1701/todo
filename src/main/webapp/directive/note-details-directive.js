toDoApp.directive('noteDetails',function(){
	return {
		restrict:'E',
		scope: {
			state:'=',
			note:'='
		},
		templateUrl:'template/note-details.html',
		controller:'noteDetailsController',
	}
});