'use strict';

function TodoListController($scope, $location, Todo) {
    $scope.todos = Todo.query();
    $scope.gotoTodoNewPage = function () {
        $location.path("/todo/new")
    };
    $scope.deleteTodo = function (todo) {
        todo.$delete({'id':todo.id}, function () {
            $location.path('/');
        });
    };
}

function TodoDetailController($scope, $routeParams, $location, Todo) {
    $scope.todo = Todo.get({id:$routeParams.id}, function (todo) {
    });
    $scope.gotoTodoListPage = function () {
        $location.path("/")
    };
    $scope.gotoEditMode = function(){
    	$location.path("/todo/edit/"+$scope.todo.id)
    };
}

function TodoEditController($scope, $routeParams, $location, TodoEdit){
	$scope.todo = TodoEdit.get({id:$routeParams.id}, function (todo) {
    });
	 $scope.submit = function () {
		 TodoEdit.save($scope.todo, function (todo) {
	            $location.path('/');
	        });
	    };
}

function TodoNewController($scope, $location, Todo) {
    $scope.submit = function () {
        Todo.save($scope.todo, function (todo) {
            $location.path('/');
        });
    };
    $scope.gotoTodoListPage = function () {
        $location.path("/")
    };
    
    $scope.master = {}; 
  
    $scope.reset = function() {
    	$scope.todo = angular.copy($scope.master);
    };
}
