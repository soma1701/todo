toDoApp.factory('homeService',function($http,$location){
	var user ={};
	user.getUser= function(){
		return $http({
			method:"POST",
			url:'getUser',
			headers:{
				'accessToken' : localStorage.getItem("accessToken")
			},
			data: user,		
		})
	}
	user.logout=function(){
		return $http({
			method:"GET",
			url:'logout',
		})
	}
	return user;
	});