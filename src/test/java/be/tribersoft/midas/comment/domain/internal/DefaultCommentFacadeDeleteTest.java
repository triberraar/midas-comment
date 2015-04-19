package be.tribersoft.midas.comment.domain.internal;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommentFacadeDeleteTest {

	private static final Long ID = 123L;
	private static final Long VERSION = 2L;
	@InjectMocks
	private DefaultCommentFacade defaultCommentFacade;
	@Mock
	private DefaultCommentRepository defaultCommentRepository;

	@Test
	public void delegatesToRepository() {
		defaultCommentFacade.delete(ID, VERSION);

		verify(defaultCommentRepository).delete(ID, VERSION);
	}
}
