package be.tribersoft.sigma.comment.rest.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.sigma.comment.domain.api.Comment;
import be.tribersoft.sigma.comment.domain.api.CommentRepository;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerGetCommentsTest {

	private static final Long ID = 123l;
	@InjectMocks
	private CommentController commentController;
	@Mock
	private CommentRepository commentRepository;
	@Mock
	private Comment comment;

	@Before
	public void setup() {
		when(commentRepository.getById(ID)).thenReturn(comment);
	}

	@Test
	public void delegatesToRepository() {
		assertSame(comment, commentController.getComment(ID));
	}

}
