package be.tribersoft.sigma.comment.domain.internal;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.sigma.comment.domain.api.exception.NotFoundException;
import be.tribersoft.sigma.comment.domain.api.exception.OptimisticLockingException;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentRepositoryGetByIdAndVersionTest {

	private static final Long ID = 123l;
	private static final Long VERSION = 2l;

	@InjectMocks
	private DefaultCommentRepository defaultCommentRepository;

	@Mock
	private CommentJpaRepository commentJpaRepository;

	@Mock
	private DefaultComment comment;

	@Test
	public void returnsCommentIfFound() {
		when(comment.getVersion()).thenReturn(VERSION);
		when(commentJpaRepository.findOne(ID)).thenReturn(comment);

		assertSame(comment, defaultCommentRepository.getByIdAndVersion(ID, VERSION));
	}

	@Test(expected = NotFoundException.class)
	public void failsIfCommentNotFound() {
		defaultCommentRepository.getByIdAndVersion(ID, VERSION);
	}

	@Test(expected = OptimisticLockingException.class)
	public void failsIfCommentOfDifferentVersion() {
		when(commentJpaRepository.findOne(ID)).thenReturn(comment);

		defaultCommentRepository.getByIdAndVersion(ID, VERSION);
	}
}
