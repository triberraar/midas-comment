package be.tribersoft.midas.comment.rest.internal;

import static com.jayway.restassured.RestAssured.given;

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
import be.tribersoft.midas.comment.domain.api.exception.NotFoundException;
import be.tribersoft.midas.comment.service.api.CommentService;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ "server.port=0", "management.port=0" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentControllerDeleteIT {

	private static final String CONTENT = "content";

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

	@Test(expected = NotFoundException.class)
	public void deletesTheComment() {
		// @formatter:off
		given().
			contentType(MediaType.APPLICATION_JSON).
			pathParam("id", comment.getId()).
			pathParam("version", comment.getVersion()).
		when().
			delete("/comments/{id}/{version}").
		then().
			statusCode(Status.NO_CONTENT.getStatusCode());
		// @formatter:on

		commentRepository.getById(comment.getId());
	}

}
