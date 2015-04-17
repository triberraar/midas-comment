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
public class DefaultCommentFacadeUpdateTest {

	private static final String NEW_CONTENT = "new content";
	private static final Long ID = 123L;
	private static final Long LONG = 2L;
	@InjectMocks
	private DefaultCommentFacade defaultCommentFacade;
	@Mock
	private CommentRequest commentRequest;
	@Mock
	private DefaultComment defaultComment;
	@Mock
	private DefaultCommentRepository defaultCommentRepository;

	@Before
	public void setup() {
		when(defaultCommentRepository.getByIdAndVersion(ID, LONG)).thenReturn(defaultComment);
		when(commentRequest.getContent()).thenReturn(NEW_CONTENT);
	}

	@Test
	public void updatesTheContentOfAComment() {
		Comment updatedComment = defaultCommentFacade.update(ID, LONG, commentRequest);

		assertSame(defaultComment, updatedComment);
		verify(defaultComment).setContent(NEW_CONTENT);
	}
}
