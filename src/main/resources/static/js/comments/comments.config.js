'use strict';

angular.module('comments.config', [ 'comments.resource', 'comments.controller', 'ngRoute' ])
		.config(function($routeProvider) {
			$routeProvider.when('/comments', {
				templateUrl : '/partials/comments.html',
				controller : 'CommentsController',
				controllerAs : 'CommentsController'
			}).otherwise({
				redirectTo : '/comments'
			});
		});