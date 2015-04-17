'use strict';

angular.module('comments.resource', ['ngResource'])
.factory('CommentsResource', function ($resource) {
        return $resource('/comments/:id', {}, {
        	create: {method: 'POST'},
        	update: {method: 'PUT'}
        });
    });