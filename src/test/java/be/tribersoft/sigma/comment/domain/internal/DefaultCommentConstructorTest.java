package be.tribersoft.sigma.comment.domain.internal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import be.tribersoft.sigma.comment.domain.api.exception.MandatoryFieldException;

public class DefaultCommentConstructorTest {

	private static final String CONTENT = "content";

	@Test
	public void createsComment() {
		DefaultComment comment = new DefaultComment(CONTENT);

		assertEquals(CONTENT, comment.getContent());
	}

	@Test(expected = MandatoryFieldException.class)
	public void failsWhenContentIsNull() {
		new DefaultComment(null);
	}

	@Test(expected = MandatoryFieldException.class)
	public void failsWhenContentIsEmpty() {
		new DefaultComment("");
	}
}
