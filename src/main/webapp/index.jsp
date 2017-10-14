<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="style/login.css">
<link rel="stylesheet" type="text/css" href="style/registration.css">
<link rel="stylesheet" type="text/css" href="style/registration.css">
<link rel="stylesheet" type="text/css" href="style/homePage.css">
<script type="text/javascript" src="bower_components/angular/angular.min.js"></script>
<script type="text/javascript" src="bower_components/angular/angular-ui-router.min.js"></script>
<script src="script/toDo.js"></script>
<script src="services/RegistrationServices.js"></script>
<script src="controller/registrationController.js"></script>
<script type="text/javascript" src ="services/loginService.js"></script>
<script type="text/javascript" src="controller/loginController.js"></script>
<script type="text/javascript" src="services/noteService.js"></script>
<script type="text/javascript" src="controller/noteController.js"></script>
</head>
<body ng-app="toDoApp">

	<div ui-view></div>
	
</body>
</html>