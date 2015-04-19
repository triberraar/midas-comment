package be.tribersoft.midas.comment.domain.internal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.midas.comment.domain.api.exception.NotFoundException;
import be.tribersoft.midas.comment.domain.api.exception.OptimisticLockingException;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentRepositoryDeleteTest {

	private static final Long ID = 123l;
	private static final Long VERSION = 2l;

	@InjectMocks
	private DefaultCommentRepository defaultCommentRepository;

	@Mock
	private CommentJpaRepository commentJpaRepository;

	@Mock
	private DefaultComment comment;

	@Test
	public void deletesCommentIfFound() {
		when(comment.getVersion()).thenReturn(VERSION);
		when(commentJpaRepository.findOne(ID)).thenReturn(comment);

		defaultCommentRepository.delete(ID, VERSION);

		verify(commentJpaRepository).delete(comment);
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
