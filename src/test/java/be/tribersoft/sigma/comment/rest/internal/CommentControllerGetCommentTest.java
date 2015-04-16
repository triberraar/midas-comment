package be.tribersoft.sigma.comment.rest.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.sigma.comment.domain.api.Comment;
import be.tribersoft.sigma.comment.domain.api.CommentRepository;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerGetCommentTest {

	@InjectMocks
	private CommentController commentController;
	@Mock
	private CommentRepository commentRepository;
	@Mock
	private Comment comment1, comment2;
	private List<Comment> comments;

	@Before
	public void setup() {
		comments = Arrays.asList(comment1, comment2);
		doReturn(comments).when(commentRepository).findAll();
	}

	@Test
	public void delegatesToRepository() {
		assertSame(comments, commentController.getComments());
	}

}
