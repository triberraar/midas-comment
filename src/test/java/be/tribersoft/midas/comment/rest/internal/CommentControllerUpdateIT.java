package be.tribersoft.midas.comment.rest.internal;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import be.tribersoft.midas.comment.Application;
import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.domain.api.CommentRepository;
import be.tribersoft.midas.comment.domain.api.CommentRequest;
import be.tribersoft.midas.comment.service.api.CommentService;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ "server.port=0", "management.port=0" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentControllerUpdateIT {

	private static final String CONTENT = "content";
	private static final String NEW_CONTENT = "new content";

	@Value("${local.server.port}")
	int port;

	@Inject
	private CommentRepository commentRepository;
	@Inject
	private CommentService commentService;
	private Comment comment;

	@Before
	public void setup() {
		RestAssured.port = port;
		comment = commentService.create(new CommentRequest() {

			@Override
			public String getContent() {
				return CONTENT;
			}
		});
	}

	@Test
	public void updatesTheComment() {
		// @formatter:off
		given().
			contentType(MediaType.APPLICATION_JSON).
			body(new DummyComment()).
			pathParam("id", comment.getId()).
			pathParam("version", comment.getVersion()).
		when().
			put("/comments/{id}/{version}").
		then().
			statusCode(Status.OK.getStatusCode()).
			body("size()", equalTo(3)).
			body("content", equalTo(NEW_CONTENT)).
			body("id", equalTo(comment.getId().intValue())).
			body("version", notNullValue()).
		extract().
			response();
		// @formatter:on

		Comment updatedComment = commentRepository.getById(comment.getId());
		assertNotNull(updatedComment);
		assertEquals(comment.getId(), updatedComment.getId());
		assertTrue(updatedComment.getVersion() > comment.getVersion());
		assertEquals(NEW_CONTENT, updatedComment.getContent());
	}

	private static class DummyComment implements CommentRequest {

		@Override
		public String getContent() {
			return NEW_CONTENT;
		}

	}
}
