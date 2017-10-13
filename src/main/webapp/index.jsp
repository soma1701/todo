<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/login.css">
<link rel="stylesheet" type="text/css" href="style/registration.css">
<link rel="stylesheet" type="text/css" href="style/registration.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.6/angular.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.min.js"></script>

<script src="script/toDo.js"></script>
<script src="controller/registrationController.js"></script>
<script src="services/RegistrationServices.js"></script>
<script type="text/javascript" src ="services/loginService.js"></script>
<script type="text/javascript" src="controller/loginController.js"></script>
<script type="text/javascript" src="controller/homeController.js"></script>

</head>
<body ng-app="toDoApp">

	<div ui-view></div>
	
</body>
</html>