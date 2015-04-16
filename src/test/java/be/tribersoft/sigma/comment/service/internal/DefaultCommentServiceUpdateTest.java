package be.tribersoft.sigma.comment.service.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.sigma.comment.domain.api.Comment;
import be.tribersoft.sigma.comment.domain.api.CommentFacade;
import be.tribersoft.sigma.comment.domain.api.CommentRequest;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentServiceUpdateTest {

	private static final Long ID = 123L;
	private static final Long VERSION = 2L;
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
		when(commentFacade.update(ID, VERSION, commentRequest)).thenReturn(comment);

		assertSame(comment, defaultCommentService.update(ID, VERSION, commentRequest));
	}
}
