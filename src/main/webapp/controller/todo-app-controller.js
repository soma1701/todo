toDoApp.controller('todoAppController', function($scope, dataStore, $uibModal, labelService,homeService, $rootScope,$location) {
	
	
	$scope.searchText;
	$scope.doSearch = function(){
		dataStore.searchData($scope.searchText);
	}
	$scope.$watch('searchText', $scope.doSearch);
	$scope.isGridView = true;
	$scope.view = "grid";
	$scope.width = 0;
	$scope.editLabelFocus = false;
	$scope.openSideBar = false;
	$scope.notes = {};
	$scope.labels = {};
	$scope.newLabel = '';
	$scope.background = "#fb0";
	var path = $location.path();
	var labelName = path.substr(path.lastIndexOf("/")+1);
	
	getlabels();
	function getlabels(){
		var httpGetLabels = labelService.getLabels(labelName);
		httpGetLabels.then(function(response) {
			console.log(response.data);
			$scope.labels = response.data;
		}, function(response) {
			if(response.status=='400')
				$location.path('/loginPage')
		});
	}
//	getUser();
//	function getUser(){
		var httpGetUser = homeService.getUser();
		httpGetUser.then(function(response) {
			console.log(response.data);
			$scope.user = response.data;
		}, function(response) {
			if(response.status=='400')
				$location.path('/loginPage')
		});
//	}
	$scope.saveLabel = function(label) {
		var data = {};
		if(label === undefined){
			data.labelName = $scope.newLabel;
		}else{
			data.labelName = label.labelName;
		}
		var saveLabel = labelService.saveLabel(data);
		saveLabel.then(function(response) {
			getLabels();
			$scope.labels = response.data;
		}, function(response) {
			if(response.status=='400')
				$location.path('/loginPage')
		});
	}
	
	$scope.deleteLabel = function(label){
		labelService.deleteLabel(label);
		getlabels();
	}
	
	$scope.toggleSideBar = function(){
		var data= {};
		$scope.openSideBar = !$scope.openSideBar;
		if($scope.openSideBar){
			data.width = 250;
			data.margin = 250;
			dataStore.toggleSideBar(data);
			$scope.width = 250;
		}else{
			data.width = 0;
			data.margin = 0;
			dataStore.toggleSideBar(data);
			$scope.width = 0;
		}
		/*data.then(function(response) {
			$scope.labels = response.data;
		}, function(response) {
			if(response.status=='400')
				$location.path('/loginPage')
		});*/
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
	$scope.openLabelList = function(){
		modalInstance = $uibModal.open({
			templateUrl: 'template/label-list.html',
			windowClass: 'app-modal-window',
			scope : $scope
		});
	}
	$rootScope.$on("$locationChangeStart",function(event, next, current){
		var toLocation = next.substr(next.lastIndexOf("/")+1);
		switch (toLocation) {
	    case "notes":
	        $scope.background = "#fb0";
	        $scope.color = "black";
	        break;
	    case "archive":
	    	$scope.background = "#607D8B";
	        $scope.color = "white";
	    	break;
	    case "trash":
	    	$scope.background = "#dc42f4";
	        $scope.color = "white";
	    	break;
	    case "labels":
	    	$scope.background = "#8c41f4";
	        $scope.color = "white";
	    	break;
	}
	});
	$scope.logout=function(){
		homeService.logout();
	}
});