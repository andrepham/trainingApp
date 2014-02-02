'use strict';

angular.module('todoService', ['ngResource']).
        factory('RestServerAgent', function ($resource) {
            return $resource('rest/todo/:id', {}, {
                'save': {method:'PUT'}
            });
        });
