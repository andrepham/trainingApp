'use strict';

function TodoListController($scope, $location, RestServerAgent) {
    $scope.todos = RestServerAgent.query();
    $scope.gotoTodoNewPage = function () {
        $location.path("/todo/new");
    };
    $scope.deleteTodo = function (todo) {
        todo.$delete({'id':todo.id}, function () {
            $location.path('/');
        });
    };
}

function TodoDetailController($scope, $routeParams, $location, RestServerAgent) {
    $scope.todo = RestServerAgent.get({id:$routeParams.id}, function (todo) {
    });
    $scope.gotoTodoListPage = function () {
        $location.path("/");
    };
    $scope.gotoEditMode = function(){
    	$location.path("/todo/edit/"+$scope.todo.id);
    };
}

function TodoEditController($scope, $routeParams, $location, RestServerAgent){
	$scope.todo = RestServerAgent.get({id:$routeParams.id}, function (todo) {
    });
	$scope.submit = function () {
		RestServerAgent.save($scope.todo, function (todo) {
	            $location.path('/');
	        });
	    };
	$scope.gotoTodoListPage = function () {
	     $location.path("/");
	};
}

	function TodoNewController($scope, $location, $parse, $http, $q, RestServerAgent) {
	    $scope.submit = function () {
			RestServerAgent.save($scope.todo, function (data) {
				var fieldsToCheck=new Array("title","description");
				var errorCount = 0;
				for(var i =0; i<=fieldsToCheck.length; i++){
					if(fieldsToCheck[i] in data){
						var field = $parse("newForm."+fieldsToCheck[i]+".$error.serverMessage");
						 field.assign($scope, data[fieldsToCheck[i]]);
						 errorCount++;
					}
				}
				if(errorCount==0){
					$location.path("/");
				}
		    });
	    };
    $scope.gotoTodoListPage = function () {
        $location.path("/");
    };
    $scope.master = {}; 
    $scope.reset = function() {
    	$scope.todo = angular.copy($scope.master);
    };
}
