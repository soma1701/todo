toDoApp.directive('navBar',function(){
	return{
		restrict:'E',
		scope: {
			user: '=user',
			callback: '&',
			callback1: '&'
		},
		replace: true,
		templateUrl:'template/nav-bar.html',
		link: function(scope,e,a){
		}
	};
});