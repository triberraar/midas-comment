package be.tribersoft.midas.comment.service.internal;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.domain.api.CommentFacade;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentServiceDeleteTest {

	private static final Long ID = 123L;
	private static final Long VERSION = 2L;
	@InjectMocks
	private DefaultCommentService defaultCommentService;
	@Mock
	private CommentFacade commentFacade;
	@Mock
	private Comment comment;

	@Test
	public void delegatesToFacade() {
		defaultCommentService.delete(ID, VERSION);

		verify(commentFacade).delete(ID, VERSION);
	}
}
