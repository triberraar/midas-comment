'use strict';

angular.module('comments.controller', ['comments.resource'])
 .controller('CommentsController', function(CommentsResource) {
	 var _this = this;
	 
	 _this.init = function() {
		 CommentsResource.query().$promise.then(function(result){
			 _this.comments = result;
		 }, function(error){
			 console.error('something went wrong');
		 });
	 };
	 
	 _this.init();
 	
 });