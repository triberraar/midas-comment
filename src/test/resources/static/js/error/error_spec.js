describe('error', function() {
	
	beforeEach(module('error'));
	
	describe('ErrorService', function() {
		
		var errorService;
		beforeEach(function() {
			inject(function(ErrorService) {
				errorService = ErrorService;
			})
		});
		
		describe('getError and setError', function() {
			it('should return the error', function() {
				errorService.setError('test error');
				
				expect(errorService.getError()).toEqual('test error');
			});
		});
		
		describe('clear', function() {
			it('should clear the error', function() {
				expect(errorService.getError()).toBeUndefined();
				
				errorService.setError('test Error');

				expect(errorService.getError()).not.toBeUndefined();
				
				errorService.clear();
				
				expect(errorService.getError()).toBeUndefined();
			});
		});
	});
	
	describe('ErrorController', function() {
		
		var controller;
		var errorServiceMock = {}
		beforeEach(inject(function($controller) {
			controller = $controller;
//			httpBackend = $httpBackend;
			
		}));
		
		var createController = function() {
			return controller('ErrorController', {
				'ErrorService': errorServiceMock
			});
		}
		
//		afterEach(function() {
//		     httpBackend.verifyNoOutstandingExpectation();
//		     httpBackend.verifyNoOutstandingRequest();
//		   });
		
//		describe('init', function() {
//			it('should query the backend for all comments', function() {
//				httpBackend.when('GET', '/comments').respond([{content: 'content1'}, {content: 'content2'}]);
				
//				var errorController = createController();
//				httpBackend.flush();
//				
//				expect(errorController.comments.length).toEqual(2);
//			})
//		})
		describe('getError', function() {
			it('should delete to ErrorService', function() {
				errorServiceMock.getError = function() {
					return 'test error';
				}
				var errorController = createController();
				
				expect(errorController.getError()).toEqual('test error');
			});
		});
		
		describe('hasError', function() {
			it('should return true if errorService has an error', function() {
				errorServiceMock.getError = function() {
					return 'test error';
				}
				
				var errorController = createController();
				
				expect(errorController.hasError()).toEqual(true);
			});
			
			it('should return false if errorService ahs no error', function() {
				errorServiceMock.getError = function() {
					return undefined;
				}
				
				var errorController = createController();
				
				expect(errorController.hasError()).toEqual(false);
			})
		})
	})
})