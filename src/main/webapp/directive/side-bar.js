toDoApp.directive('sideBar',function(){
	return{
		restrict:'E',
		scope: {
			width:'='
		},
		replace: true,
		templateUrl:'template/side-bar.html',
		link: function(scope,e,a){
		}
	};
});