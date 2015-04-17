package be.tribersoft.midas.comment.rest.internal;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.domain.api.CommentRepository;
import be.tribersoft.midas.comment.service.api.CommentService;

@Path("/comments")
public class CommentController {

	@Inject
	private CommentRepository commentRepository;

	@Inject
	private CommentService commentService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<? extends Comment> getComments() {
		return commentRepository.findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getComment(@PathParam("id") Long id) {
		return commentRepository.getById(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment create(CommentFromJsonAdapter commentRequest) {
		return commentService.create(commentRequest);
	}

	@PUT
	@Path("/{id}/{version}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment update(@PathParam("id") Long id, @PathParam("version") Long version, CommentFromJsonAdapter commentFromJsonAdapter) {
		return commentService.update(id, version, commentFromJsonAdapter);
	}
}
