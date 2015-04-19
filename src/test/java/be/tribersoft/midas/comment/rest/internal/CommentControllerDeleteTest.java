package be.tribersoft.midas.comment.rest.internal;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.service.api.CommentService;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerDeleteTest {

	private static final Long ID = 123l;
	private static final Long VERSION = 32L;
	@InjectMocks
	private CommentController commentController;
	@Mock
	private CommentService commentService;
	@Mock
	private Comment comment;

	@Test
	public void delegatesToService() {
		commentController.delete(ID, VERSION);

		verify(commentService).delete(ID, VERSION);
	}

}
