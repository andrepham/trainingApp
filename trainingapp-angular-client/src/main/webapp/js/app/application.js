'use strict';

angular.module('todoApp', ['todoService','todoEditService']).
        config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
            when('/todo/list', {templateUrl:'views/todo-list.html', controller:TodoListController}).
            when('/todo/new', {templateUrl:'views/todo-new.html', controller:TodoNewController}).
            when('/todo/:id', {templateUrl:'views/todo-detail.html', controller:TodoDetailController}).
            when('/todo/edit/:id', {templateUrl:'views/todo-edit.html', controller:TodoEditController}).
            otherwise({redirectTo:'/todo/list'});
}]);
