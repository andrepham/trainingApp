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
    	
    function checkForm(){
    	var deferred = $q.defer();
    	
    	(function(){
			
			$http.post("rest/todo/validate",$scope.todo).success(function(formErrors){
				var noError = jQuery.isEmptyObject(formErrors);
				if(!noError){
	    			for (var fieldName in formErrors) {
	       			 var message = formErrors[fieldName];
	       			 var field = $parse('newForm.'+fieldName+'.$error.serverMessage');
	       			 $scope.newForm.$setValidity(fieldName, false);
	       			 field.assign($scope, message);
	       		 	}
	    			deferred.reject("at least one error");
				}
				else{
					deferred.resolve("No error");
				}
    		});
			
		}).call();
    	
    	return deferred.promise;
    }	
    
    var promise = checkForm();
    promise.then(function(){
    	RestServerAgent.save($scope.todo, function (todo) {
            $location.path('/');
        });
    }, function(){
    	alert("There are some errors...");
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
