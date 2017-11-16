toDoApp.controller('todoAppController', function($scope, dataStore, $uibModal, labelService) {
	
	$scope.isGridView = true;
	$scope.view = "grid";
	$scope.width = 0;
	$scope.editLabelFocus = false;
	$scope.openSideBar = false;
	$scope.notes = {};
	$scope.labels = {};
	$scope.newLabel = 'test';
	var httpGetLabels = labelService.getLabels('LABELS');

	/*httpGetLabels.then(function(response) {
		console.log(response.data);
		$scope.labels = response.data;
	}, function(response) {
		if(response.status=='511')
			$location.path('/loginPage')
		console.log(response);
	});*/
	$scope.labels = httpGetLabels;
	
	$scope.saveLabel = function() {
		var data = {
				labelName : $scope.newModel
		}
		labelService.saveLabel(data);
	}
	
	$scope.deleteLabel = function(label){
		labelService.deleteLabel(label);
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
			scope : $scope
		});
	}
});