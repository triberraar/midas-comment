describe('comments.controller', function() {
	
	beforeEach(module('comments.controller'));
	
	var controller, httpBack;
	var errorServiceMock = {
			clear: function(){},
			setError: function(error){}
	}
	beforeEach(inject(function($controller, $httpBackend) {
		controller = $controller;
		httpBackend = $httpBackend;
		
	}));
	
	var createController = function() {
		return controller('CommentsController', {
			'ErrorService': errorServiceMock
		});
	}
	
	var createControllerWithExpectations = function() {
		httpBackend.expectGET( 'comments').respond(200, [{content: 'content1'}, {content: 'content2'}]);
		var controller = createController();
		httpBackend.flush();
		
		return controller;
	}
	
	afterEach(function() {
	     httpBackend.verifyNoOutstandingExpectation();
	     httpBackend.verifyNoOutstandingRequest();
	});
	
	describe('init', function() {
		it('should get all comments from backend when succesful', function() {
			httpBackend.expectGET('comments').respond(200, [{content: 'content1'}, {content: 'content2'}]);
			
			var controller = createController();
			httpBackend.flush();
			
			expect(controller.comments.length).toEqual(2);
			expect(controller.comments[0].content).toEqual('content1');
			expect(controller.comments[1].content).toEqual('content2');
		});
		it('should handle error  when not succesful', function() {
			httpBackend.when('GET' , 'comments').respond(500, {message: 'message'});
			spyOn(errorServiceMock, 'setError');
			
			var controller = createController();
			httpBackend.flush();
			
			expect(controller.comments).toBeUndefined();
			expect(errorServiceMock.setError).toHaveBeenCalledWith('Could not retrieve comments');
			
		});
	});
	
	describe('edit', function() {
		it('should deep clone the comment to be edited', function() {
			var controller = createControllerWithExpectations();
			
			controller.edit({content: 'content'});
			
			expect(controller.comment).toEqual({content: 'content'});
		});
	});
	
	describe('add', function() {
		beforeEach( function() {
			spyOn(errorServiceMock, 'clear');
			spyOn(errorServiceMock, 'setError');
		});
		it('should post to backend', function() {
			var controller = createControllerWithExpectations();
			var comment={content: 'content'};
			controller.comment = comment;
			var formMock = {$valid: true};
			
			httpBackend.expectPOST('comments', comment).respond(200, comment);
			httpBackend.expectGET('comments').respond(200, [comment]);
			controller.add(formMock);
			httpBackend.flush();
			
			expect(controller.comments.length).toEqual(1);
			expect(controller.comments[0].content).toEqual(comment.content);
			expect(errorServiceMock.clear).toHaveBeenCalled();
		});
		
		it('should do nothing when form is not valid', function() {
			var controller = createControllerWithExpectations();
			var formMock = {$valid: false};
			
			controller.add(formMock);
			
		});
		
		it('should handle error when post fails', function() {
			var controller = createControllerWithExpectations();
			var comment={content: 'content'};
			controller.comment = comment;
			var formMock = {$valid: true};
			
			httpBackend.expectPOST('comments', comment).respond(500);
			controller.add(formMock);
			httpBackend.flush();
			
			expect(errorServiceMock.setError).toHaveBeenCalledWith('Could not create comment');
		});
	});
	
	describe('update', function() {
		beforeEach( function() {
			spyOn(errorServiceMock, 'clear');
			spyOn(errorServiceMock, 'setError');
		});
		it('should put to backend', function() {
			var controller = createControllerWithExpectations();
			var comment={content: 'content', id: 12, version: 45};
			controller.comment = comment;
			var formMock = {$valid: true};
			
			httpBackend.expectPUT('comments/12/45', comment).respond(200, comment);
			httpBackend.expectGET('comments').respond(200, [comment]);
			controller.update(formMock);
			httpBackend.flush();
			
			expect(controller.comments.length).toEqual(1);
			expect(controller.comments[0].content).toEqual(comment.content);
			expect(errorServiceMock.clear).toHaveBeenCalled();
		});
		
		it('should do nothing when form is not valid', function() {
			var controller = createControllerWithExpectations();
			var formMock = {$valid: false};
			
			controller.update(formMock);
			
		});
		
		it('should handle error when put fails', function() {
			var controller = createControllerWithExpectations();
			var comment={content: 'content', id: 12, version: 45};
			controller.comment = comment;
			var formMock = {$valid: true};
			
			httpBackend.expectPUT('comments/12/45', comment).respond(500);
			controller.update(formMock);
			httpBackend.flush();
			
			expect(errorServiceMock.setError).toHaveBeenCalledWith('Could not update comment');
		})
	})
	
	describe('remove', function() {
		beforeEach( function() {
			spyOn(errorServiceMock, 'clear');
			spyOn(errorServiceMock, 'setError');
		});
		it('should delete to backend', function() {
			var controller = createControllerWithExpectations();
			var comment={content: 'content', id: 12, version: 45};
			controller.comment = comment;
			var formMock = {$valid: true};
			
			httpBackend.expectDELETE('comments/12/45').respond(200);
			httpBackend.expectGET('comments').respond(200, [comment]);
			controller.remove(comment);
			httpBackend.flush();
			
			expect(controller.comments.length).toEqual(1);
			expect(controller.comments[0].content).toEqual(comment.content);
			expect(errorServiceMock.clear).toHaveBeenCalled();
		});
		
		it('should handle error when delete fails', function() {
			var controller = createControllerWithExpectations();
			var comment={content: 'content', id: 12, version: 45};
			controller.comment = comment;
			var formMock = {$valid: true};
			
			httpBackend.expectDELETE('comments/12/45').respond(500);
			controller.remove(comment);
			httpBackend.flush();
			
			expect(errorServiceMock.setError).toHaveBeenCalledWith('Could not delete comment');
		})
	})
});