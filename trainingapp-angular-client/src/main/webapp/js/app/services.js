'use strict';

angular.module('todoService', ['ngResource']).
        factory('Todo', function ($resource) {
            return $resource('rest/todo/:id', {}, {
                'save': {method:'PUT'}
            });
        });

angular.module('todoEditService', ['ngResource']).
factory('TodoEdit', function ($resource) {
    return $resource('rest/todo/edit/:id', {}, {
        'save': {method:'PUT'}
    });
});