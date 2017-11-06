toDoApp.directive('navBar',function(){
	return{
		restrict:'E',
		scope: {
			user: '=user'
		},
		replace: true,
		templateUrl:'template/nav-bar.template.html',
		link: function(scope,e,a){
//			console.log(a.user);
		scope.dataa = a.data;
		}
	};
});