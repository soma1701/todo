var toDoApp = angular.module('toDoApp')
toDoApp.controller('modalController',function ($scope, $uibModal) {
	$scope.open = function () {
		console.log('opening pop up');
		var modalInstance = $uibModal.open({
		templateUrl: 'template/modal.html'
		});
		}
		});
	