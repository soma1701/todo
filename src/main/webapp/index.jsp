<head>
<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="style/login.css">
	<link rel="stylesheet" type="text/css" href="style/registration.css">
	<link rel="stylesheet" type="text/css" href="style/registration.css">
	<link rel="stylesheet" type="text/css" href="style/homePage.css">
	<script type="text/javascript" src="bower_components/angular/angular.min.js"></script>
	<script type="text/javascript" src="bower_components/angular/angular-ui-router.min.js"></script>
	<script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
	<script type="text/javascript" src="bower_components/ng-file-upload/ng-file-upload.min.js"></script>
	<script type="text/javascript" src="bower_components/ng-file-upload/ng-file-upload-all.js"></script>
	<script src="script/toDo.js"></script>
	<script type="text/javascript" src="directive/nav-bar.js"></script>
	<script type="text/javascript" src="directive/side-bar.js"></script>
	<script type="text/javascript" src="directive/fileupload-directive.js"></script>
	<script type="text/javascript" src="directive/note-details-directive.js"></script>
	<script type="text/javascript" src="directive/action-bar-directive.js"></script>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="bower_components/angular-bootstrap-datetimepicker/src/js/datetimepicker.js"></script>
	<script type="text/javascript" src="bower_components/angular-bootstrap-datetimepicker/src/js/datetimepicker.templates.js"></script>
	<script src="services/RegistrationServices.js"></script>
	<script src="controller/registrationController.js"></script>
	<script type="text/javascript" src ="services/loginService.js"></script>
	<script type="text/javascript" src ="services/data-store-service.js"></script>
	<script type="text/javascript" src ="services/home-page-service.js"></script>
	<script type="text/javascript" src ="services/fileupload-service.js"></script>
	<script type="text/javascript" src ="services/label-service.js"></script>
	<script type="text/javascript" src="controller/loginController.js"></script>
	<script type="text/javascript" src="services/noteService.js"></script>
	<script type="text/javascript" src="controller/noteController.js"></script>
	<script type="text/javascript" src="controller/note-details-controller.js"></script>
	<script type="text/javascript" src="controller/action-bar-controller.js"></script>
	<script type="text/javascript" src="controller/archive-controller.js"></script>
	<script type="text/javascript" src="controller/labels-controller.js"></script>
	<script type="text/javascript" src="controller/trash-controller.js"></script>
	<script type="text/javascript" src="controller/reminder-controller.js"></script>
	<script type="text/javascript" src="controller/todo-app-controller.js"></script>
	<script type="text/javascript" src="controller/fileupload-controller.js"></script>
	<script src="https://connect.facebook.net/enUS/all.js"></script>
	<script src="https://npmcdn.com/angular-toastr/dist/angular-toastr.tpls.js"></script>
	<link rel="stylesheet" href="https://npmcdn.com/angular-toastr/dist/angular-toastr.css" />
	
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->

<!-- <!-- Optional theme --> 
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" /> -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	
	<script type="text/javascript" src="controller/googleController.js"></script>
	<script type="text/javascript" src="controller/fbController.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	
</head>
<body ng-app="toDoApp">
	 <div ui-view></div>
</body>
</html>