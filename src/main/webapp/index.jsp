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
<!-- 	<script type="text/javascript" src="script/ui-bootstrap-tpls-2.5.0.min.js"></script> -->
	<script type="text/javascript" src="directive/nav-bar.js"></script>
	<script type="text/javascript" src="directive/side-bar.js"></script>
	<script type="text/javascript" src="directive/fileupload-directive.js"></script>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.js"></script>
	
	<script src="services/RegistrationServices.js"></script>
	<script src="controller/registrationController.js"></script>
	<script type="text/javascript" src ="services/loginService.js"></script>
	<script type="text/javascript" src ="services/data-store-service.js"></script>
	<script type="text/javascript" src ="services/fileupload-service.js"></script>
	<script type="text/javascript" src="controller/loginController.js"></script>
	<script type="text/javascript" src="services/noteService.js"></script>
	<script type="text/javascript" src="controller/noteController.js"></script>
	<script type="text/javascript" src="controller/archive-controller.js"></script>
	<script type="text/javascript" src="controller/trash-controller.js"></script>
	<script type="text/javascript" src="controller/todo-app-controller.js"></script>
	<script type="text/javascript" src="controller/fileupload-controller.js"></script>
	
<!-- 	<script type="text/javascript" src="directive/navigationbar.js"></script> -->
<!-- 	<script type="text/javascript" src="directive/note.directive.js"></script> -->
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
		body {
		    font-family: "Lato", sans-serif;
		}
		
		.sidenav {
		    height: 100%;
		    width: 0;
		    position: fixed;
		    z-index: 1;
		    top: 0;
		    left: 0;
		    background-color: #111;
		    overflow-x: hidden;
		    transition: 0.5s;
		    padding-top: 60px;
		    margin-top:60px;
		}
		
		.sidenav a {
		    padding: 8px 8px 8px 32px;
		    text-decoration: none;
		    font-size: 25px;
		    color: #818181;
		    display: block;
		    transition: 0.3s;
		}
		
		.sidenav a:hover {
		    color: #f1f1f1;
		}
		
		.sidenav .closebtn {
		    position: absolute;
		    top: 0;
		    right: 25px;
		    font-size: 36px;
		    margin-left: 50px;
		}
		
		#main {
		    transition: margin-left .5s;
		    padding: 0px;
		}
		
		@media screen and (max-height: 450px) {
		  .sidenav {padding-top: 15px;}
		  .sidenav a {font-size: 18px;}
		}
	</style>
	
</head>
<body ng-app="toDoApp">
	<div ui-view></div>
	
</body>
</html>