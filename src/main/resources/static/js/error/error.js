'use strict';

angular.module('error', [])
	.directive('error', function() {
		return {
			templateUrl : 'js/error/error.html',
			controller : 'ErrorController',
			controllerAs : 'errorController'
		}
	}).factory('ErrorService', function() {
		var _error;
	
		var _setError = function(error) {
			_error = error;
		};
	
		var _getError = function() {
			return _error;
		};
	
		var _clear = function() {
			_error = undefined;
		};
	
		return {
			getError : _getError,
			clear : _clear,
			setError : _setError
		}
	}).controller('ErrorController', function(ErrorService) {
		var _this = this;
	
		_this.getError = function() {
			return ErrorService.getError();
		}
	
		_this.hasError = function() {
			return ErrorService.getError() !== undefined;
		}
	
	});