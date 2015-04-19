'use strict';

angular.module('comments.resource', ['ngResource'])
.factory('CommentsResource', function ($resource) {
        return $resource('/comments/:id/:version', {}, {
        	create: {method: 'POST'},
        	update: {method: 'PUT'},
        	remove: {method: 'DELETE'}
        });
    });