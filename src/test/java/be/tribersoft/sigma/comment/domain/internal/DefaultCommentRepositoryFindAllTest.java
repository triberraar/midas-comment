package be.tribersoft.sigma.comment.domain.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentRepositoryFindAllTest {

	@InjectMocks
	private DefaultCommentRepository defaultCommentRepository;

	@Mock
	private CommentJpaRepository commentJpaRepository;

	@Mock
	private DefaultComment comment;

	@Test
	public void savesComment() {
		when(commentJpaRepository.save(comment)).thenReturn(comment);

		assertSame(comment, defaultCommentRepository.save(comment));
	}
}
