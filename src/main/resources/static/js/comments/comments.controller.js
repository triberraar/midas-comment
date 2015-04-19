'use strict';

angular.module('comments.controller', ['comments.resource',])
 .controller('CommentsController', function(CommentsResource, ErrorService) {
	 var _this = this;
	 
	 _this.init = function() {
		 ErrorService.clear();
		 CommentsResource.query().$promise.then(function(result){
			 _this.comments = result;
		 }, function(error){
			 ErrorService.setError('Could not retrieve comments');
		 });
		 _this.comment = {};
	 };
	 
	 _this.edit = function(comment) {
		 _this.comment = _.cloneDeep(comment);
	 }
	 
	 _this.add = function(commentForm) {
		 ErrorService.clear();
		 if(!commentForm.$valid) {
			 return;
		 }
		 CommentsResource.create(_this.comment).$promise.then( function(result) {
			 _this.init();
		 }, function(error){
			 ErrorService.setError('Could not create comment');
		 });
	 }
	 
	 _this.update = function(commentForm) {
		 ErrorService.clear();
		 if(!commentForm.$valid) {
			 return;
		 }
		 CommentsResource.update({id: _this.comment.id, version: _this.comment.version}, _this.comment).$promise.then(function(result){
			 _this.init();
		 }, function(error){
			 ErrorService.setError('Could not update comment');
		 });
	 }
	 
	 _this.remove = function(comment) {
		 ErrorService.clear();
		 CommentsResource.remove({id: comment.id, version: comment.version}).$promise.then( function(result){
			 _this.init();
		 }, function(error){
			 ErrorService.setError('Could not delete comment');
		 });
	 }
	 
	 _this.init();
 	
 });