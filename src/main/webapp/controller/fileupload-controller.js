toDoApp.controller('myCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
    
    $scope.uploadFile = function(){
        var file = $scope.myFile;
        console.log("file is "+file );
        console.dir(file);
        var uploadUrl = "/home/bridgeit/Downloads/aa65f5dc-e84e-4f7a-8ab1-77161003cbbe.jpg";
        fileUpload.uploadFileToUrl(file, uploadUrl);
    };
    
}]);

/*toDoApp.controller('myCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
    
    $scope.controller('UploadController', function($scope, fileReader) {
        $scope.imageSrc = "";
        
        $scope.$on("fileProgress", function(e, progress) {
          $scope.progress = progress.loaded / progress.total;
        });
      });
}]);*/