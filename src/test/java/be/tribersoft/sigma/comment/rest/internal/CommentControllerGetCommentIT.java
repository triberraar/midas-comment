package be.tribersoft.sigma.comment.rest.internal;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import javax.inject.Inject;
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

import be.tribersoft.sigma.comment.Application;
import be.tribersoft.sigma.comment.domain.api.Comment;
import be.tribersoft.sigma.comment.domain.api.CommentRequest;
import be.tribersoft.sigma.comment.service.api.CommentService;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ "server.port=0", "management.port=0" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentControllerGetCommentIT {

	private static final String CONTENT = "content";

	@Value("${local.server.port}")
	int port;

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
	public void getsTheComment() {
		// @formatter:off
		given().
			pathParam("id", comment.getId()).
		when().
			get("/comments/{id}").
		then().
			statusCode(Status.OK.getStatusCode()).
			body("size()", equalTo(3)).
			body("content", equalTo(CONTENT)).
			body("id", equalTo(comment.getId().intValue())).
			body("version", equalTo(comment.getVersion().intValue()));
		// @formatter:on
	}

	@Test
	public void handlesUnfoundComment() {
		// @formatter:off
		given().
			pathParam("id", comment.getId() + 1).
		when().
			get("/comments/{id}").
		then().
			statusCode(Status.NOT_FOUND.getStatusCode());
		// @formatter:on
	}
}
