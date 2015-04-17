package be.tribersoft.midas.comment.domain.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.domain.api.CommentRequest;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentFacadeCreateTest {

	@InjectMocks
	private DefaultCommentFacade defaultCommentFacade;
	@Mock
	private CommentRequest commentRequest;
	@Mock
	private CommentFactory commentFactory;
	@Mock
	private DefaultComment defaultComment;
	@Mock
	private DefaultCommentRepository defaultCommentRepository;

	@Before
	public void setup() {
		when(commentFactory.create(commentRequest)).thenReturn(defaultComment);
	}

	@Test
	public void createsAndSavesAComment() {
		Comment createdComment = defaultCommentFacade.create(commentRequest);

		assertSame(defaultComment, createdComment);
		verify(defaultCommentRepository).save(defaultComment);
	}
}
