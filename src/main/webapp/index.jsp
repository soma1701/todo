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
	<script src="script/toDo.js"></script>
<!-- 	<script type="text/javascript" src="script/ui-bootstrap-tpls-2.5.0.min.js"></script> -->
	<script type="text/javascript" src="directive/nav-bar.js"></script>
	<script src="services/RegistrationServices.js"></script>
	<script src="controller/registrationController.js"></script>
	<script type="text/javascript" src ="services/loginService.js"></script>
	<script type="text/javascript" src="controller/loginController.js"></script>
	<script type="text/javascript" src="services/noteService.js"></script>
	<script type="text/javascript" src="controller/noteController.js"></script>
	<script type="text/javascript" src="directive/navigationbar.js"></script>
<!-- 	<script type="text/javascript" src="directive/sidebar.js"></script> -->
<!-- 	<script type="text/javascript" src="directive/cardsinput.js"></script> -->
<!-- 	<script type="text/javascript" src="directive/cardsoutput.js"></script> -->
<!-- 	<script type="text/javascript" src="directive/modal.js"></script> -->
	<script type="text/javascript" src="controller/googleController.js"></script>
	<script type="text/javascript" src="controller/fbController.js"></script>
<!-- 	<script type="text/javascript" src="controller/modalController.js"></script> -->
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	
	<style>
    	#navBarSearchForm input[type=text]{width:550px !important;}
	</style>
	
</head>
<body ng-app="toDoApp">

	<div ui-view></div>
	
</body>
</html>