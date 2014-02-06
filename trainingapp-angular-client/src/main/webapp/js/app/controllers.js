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

function TodoNewController($scope, $location, $parse, $http,RestServerAgent) {
    $scope.submit = function () {
    	RestServerAgent.save($scope.todo, function (todo) {
    		$http.post("rest/todo/validate",$scope.todo).success(function(formErrors){
    			for (var fieldName in formErrors) {
       			 var message = formErrors[fieldName];
       			 var field = $parse('newForm.'+fieldName+'.$error.serverMessage');
       			 //alert(typeof($scope.newForm));
       			 $scope.newForm.$setValidity(fieldName, false);
       			 field.assign($scope, message);
//       			$location.path('/todo/new');
       		 }
    		});
    		 
            $location.path('/');
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
