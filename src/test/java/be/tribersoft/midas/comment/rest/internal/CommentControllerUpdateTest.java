package be.tribersoft.midas.comment.rest.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.service.api.CommentService;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerUpdateTest {

	private static final Long ID = 123l;
	private static final Long VERSION = 32L;
	@InjectMocks
	private CommentController commentController;
	@Mock
	private CommentService commentService;
	@Mock
	private Comment comment;
	@Mock
	private CommentFromJsonAdapter commentRequest;

	@Before
	public void setup() {
		when(commentService.update(ID, VERSION, commentRequest)).thenReturn(comment);
	}

	@Test
	public void delegatesToService() {
		assertSame(comment, commentController.update(ID, VERSION, commentRequest));
	}

}
