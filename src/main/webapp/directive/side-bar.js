toDoApp.directive('sideBar',function(){
	return{
		restrict:'E',
		scope: {
			width:'=',
			callback: '&',
			labels: '='
		},
		replace: true,
		templateUrl:'template/side-bar.html',
		link: function(scope,e,a){
		}
	};
});