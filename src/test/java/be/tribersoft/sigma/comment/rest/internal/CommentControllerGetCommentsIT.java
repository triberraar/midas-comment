package be.tribersoft.sigma.comment.rest.internal;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

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
import be.tribersoft.sigma.comment.domain.api.CommentRequest;
import be.tribersoft.sigma.comment.service.api.CommentService;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ "server.port=0", "management.port=0" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentControllerGetCommentsIT {

	private static final String CONTENT_1 = "content 1";
	private static final String CONTENT_2 = "content 2";

	@Value("${local.server.port}")
	int port;

	@Inject
	private CommentService commentService;

	@Before
	public void setup() {
		RestAssured.port = port;
		commentService.create(new CommentRequest() {

			@Override
			public String getContent() {
				return CONTENT_1;
			}
		});
		commentService.create(new CommentRequest() {

			@Override
			public String getContent() {
				return CONTENT_2;
			}
		});
	}

	@Test
	public void getsAllComments() {
		// @formatter:off
		when().
			get("/comments").
		then().
			statusCode(Status.OK.getStatusCode()).
			body("size()", equalTo(2)).
			body("[0].size()", equalTo(3)).
			body("[0].content", equalTo(CONTENT_1)).
			body("[0].id", notNullValue()).
			body("[0].version", notNullValue()).
			body("[1].size()", equalTo(3)).
			body("[1].content", equalTo(CONTENT_2)).
			body("[1].id", notNullValue()).
			body("[1].version", notNullValue()).
		extract().
			response();
		// @formatter:on

	}

}
