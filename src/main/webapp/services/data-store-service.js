toDoApp.service('dataStore', function ($http, $rootScope) {

    	var view;
    	var width;
    	var margin;
    	var searchText;
    	var loginUser;

        return {
            getView: getView,
            setView: setView,
            getMargin: getMargin,
            getWidth: getWidth,
            toggleSideBar: toggleSideBar,
            getSearchText: getSearchText,
            searchData: searchData,
            setLoginUser : setLoginUser,
            getLoginUser : getLoginUser
        };

        function getView() {
            return view;
        }

        function setView(value) {
        	view = value;
        	$rootScope.$broadcast('view-change', value);
        }
        function getWidth() {
            return width;
        }

        function getMargin() {
            return margin;
        }

        function toggleSideBar(data){
        	width = data.width;
        	margin = data.margin;
        	$rootScope.$broadcast('toggleSideBar-change');
        }
        function searchData(text){
        	searchText = text;
        	$rootScope.$broadcast('searchText-change');
        }
        function getSearchText(){
        	return searchText;
        }
        function setLoginUser(user){
        	loginUser = user;
        	$rootScope.$broadcast('loginUser-change');
        }
        function getLoginUser(){
        	return loginUser;
        }
});