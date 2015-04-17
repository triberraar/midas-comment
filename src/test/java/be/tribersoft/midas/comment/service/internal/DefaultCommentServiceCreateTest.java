package be.tribersoft.midas.comment.service.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.domain.api.CommentFacade;
import be.tribersoft.midas.comment.domain.api.CommentRequest;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentServiceCreateTest {

	@InjectMocks
	private DefaultCommentService defaultCommentService;
	@Mock
	private CommentFacade commentFacade;
	@Mock
	private CommentRequest commentRequest;
	@Mock
	private Comment comment;

	@Test
	public void delegatesToFacade() {
		when(commentFacade.create(commentRequest)).thenReturn(comment);

		assertSame(comment, defaultCommentService.create(commentRequest));
	}
}
