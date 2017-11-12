toDoApp.service('dataStore', function ($http, $rootScope) {

    	var view;
    	var width;
    	var margin;

        return {
            getView: getView,
            setView: setView,
            getMargin: getMargin,
            getWidth: getWidth,
            toggleSideBar: toggleSideBar
        };

        // .................

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
});