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
		 _this.comment = {};
	 };
	 
	 _this.edit = function(comment) {
		 _this.comment = _.cloneDeep(comment);
	 }
	 
	 _this.add = function(commentForm) {
		 if(!commentForm.$valid) {
			 return;
		 }
		 CommentsResource.create(_this.comment).$promise.then( function(result) {
			 _this.init();
		 }, function(error){
			 console.error('something went wrong');
		 });
	 }
	 
	 _this.update = function(commentForm) {
		 if(!commentForm.$valid) {
			 return;
		 }
		 CommentsResource.update({id: _this.comment.id, version: _this.comment.version}, _this.comment).$promise.then(function(result){
			 _this.init();
		 }, function(error){
			 console.error('something went wrong');
		 });
	 }
	 
	 _this.remove = function(comment) {
		 CommentsResource.remove({id: comment.id, version: comment.version}).$promise.then( function(result){
			 _this.init();
		 }, function(error){
			 console.error('something went wrong');
		 });
	 }
	 
	 _this.init();
 	
 });