'use strict';

angular.module('comments.config', [ 'comments.resource', 'comments.controller', 'ngRoute', 'error' ])
		.config(function($routeProvider) {
			$routeProvider.when('/comments', {
				templateUrl : 'js/comments/comments.html',
				controller : 'CommentsController',
				controllerAs : 'commentsController'
			}).otherwise({
				redirectTo : '/comments'
			});
		});