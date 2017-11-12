toDoApp.controller('todoAppController', function($scope, dataStore) {
	
	$scope.isGridView = true;
	$scope.view = "grid";
	$scope.width = 0;
//	$scope.margin = 0;
	$scope.openSideBar = false;
	
	$scope.toggleSideBar = function(){
		var data= {};
		$scope.openSideBar = !$scope.openSideBar;
		if($scope.openSideBar){
			data.width = 250;
			data.margin = 250;
			dataStore.toggleSideBar(data);
			$scope.width = 250;
//			$scope.margin = 250;
		}else{
			data.width = 0;
			data.margin = 0;
			dataStore.toggleSideBar(data);
			$scope.width = 0;
			$scope.margin = 0;
		}
	};
	$scope.switchView = function(){
		if($scope.view === "grid"){
			$scope.view = "list";
			dataStore.setView("list");
		}else{
			$scope.view = "grid";
			dataStore.setView("grid");
		}
	};
	$scope.req = {
				background:"#fb0",
				bordercolor:"#fb0",
				color:"black",
				view:$scope.view
	};
});