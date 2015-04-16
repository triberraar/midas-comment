package be.tribersoft.sigma.comment.domain.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.sigma.comment.domain.api.exception.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentRepositoryGetByIdTest {

	private static final Long ID = 123l;

	@InjectMocks
	private DefaultCommentRepository defaultCommentRepository;

	@Mock
	private CommentJpaRepository commentJpaRepository;

	@Mock
	private DefaultComment comment;

	@Test
	public void returnsCommentIfFound() {
		when(commentJpaRepository.findOne(ID)).thenReturn(comment);

		assertSame(comment, defaultCommentRepository.getById(ID));
	}

	@Test(expected = NotFoundException.class)
	public void failsIfCommentNotFound() {
		defaultCommentRepository.getById(ID);
	}
}
