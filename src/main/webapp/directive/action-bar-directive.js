toDoApp.directive('actionBar',function(){
	return {
		restrict:'E',
		scope: {
			state:'=',
			note:'='
		},
		templateUrl:'template/action-bar.html',
		controller:'actionBarController',
	}
});