package be.tribersoft.midas.comment.domain.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentRepositorySaveTest {

	@InjectMocks
	private DefaultCommentRepository defaultCommentRepository;

	@Mock
	private CommentJpaRepository commentJpaRepository;

	@Mock
	private DefaultComment comment1, comment2;

	@Test
	public void returnsAllComments() {
		List<DefaultComment> comments = Arrays.asList(comment1, comment2);
		doReturn(comments).when(commentJpaRepository).findAll();

		assertSame(comments, defaultCommentRepository.findAll());
	}
}
