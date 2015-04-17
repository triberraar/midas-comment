package be.tribersoft.midas.comment.domain.internal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import be.tribersoft.midas.comment.domain.api.CommentRequest;

@RunWith(MockitoJUnitRunner.class)
public class CommentFactoryCreateTest {

	private static final String CONTENT = "content";

	private CommentFactory commentFactory = new CommentFactory();

	@Mock
	private CommentRequest commentRequest;

	@Before
	public void setup() {
		when(commentRequest.getContent()).thenReturn(CONTENT);
	}

	@Test
	public void createsAComment() {
		DefaultComment comment = commentFactory.create(commentRequest);

		assertEquals(CONTENT, comment.getContent());
	}
}
