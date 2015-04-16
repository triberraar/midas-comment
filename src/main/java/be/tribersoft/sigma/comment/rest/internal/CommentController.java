package be.tribersoft.sigma.comment.rest.internal;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.web.bind.annotation.RestController;

import be.tribersoft.sigma.comment.domain.api.Comment;
import be.tribersoft.sigma.comment.domain.api.CommentRepository;
import be.tribersoft.sigma.comment.service.api.CommentService;

@RestController
@Path("/comments")
public class CommentController {

	@Inject
	private CommentRepository commentRepository;

	@Inject
	private CommentService commentService;

	@GET
	public List<? extends Comment> getComments() {
		return commentRepository.findAll();
	}

	@GET
	@Path("/{id}")
	public Comment getComment(@PathParam("id") Long id) {
		return commentRepository.getById(id);
	}

	@POST
	public Comment create(CommentFromJsonAdapter commentRequest) {
		return commentService.create(commentRequest);
	}

	@PUT
	@Path("/{id}/{version}")
	public Comment update(@PathParam("id") Long id, @PathParam("version") Long version, CommentFromJsonAdapter commentFromJsonAdapter) {
		return commentService.update(id, version, commentFromJsonAdapter);
	}
}
