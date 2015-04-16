package be.tribersoft.sigma.comment.rest.internal;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import be.tribersoft.sigma.comment.Application;
import be.tribersoft.sigma.comment.domain.api.Comment;
import be.tribersoft.sigma.comment.domain.api.CommentRepository;
import be.tribersoft.sigma.comment.domain.api.CommentRequest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ "server.port=0", "management.port=0" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentControllerCreateIT {

	private static final String CONTENT = "content";

	@Value("${local.server.port}")
	int port;

	@Inject
	private CommentRepository commentRepository;

	@Before
	public void setup() {
		RestAssured.port = port;
	}

	@Test
	public void createsAComment() {
		// @formatter:off
		Response response = given().
			contentType(MediaType.APPLICATION_JSON).
			body(new DummyComment()).
		when().
			post("/comments").
		then().
			statusCode(Status.OK.getStatusCode()).
			body("size()", equalTo(3)).
			body("content", equalTo(CONTENT)).
			body("id", notNullValue()).
			body("version", notNullValue()).
		extract().
			response();
		// @formatter:on

		Comment comment = commentRepository.getById(Long.valueOf((Integer) response.path("id")));
		assertNotNull(comment);
		assertEquals(CONTENT, comment.getContent());
	}

	private static class DummyComment implements CommentRequest {

		@Override
		public String getContent() {
			return CONTENT;
		}

	}
}
