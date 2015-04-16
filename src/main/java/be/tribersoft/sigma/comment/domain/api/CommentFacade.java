package be.tribersoft.sigma.comment.domain.api;

public interface CommentFacade {

	Comment create(CommentRequest commentRequest);

	Comment update(Long id, Long version, CommentRequest commentRequest);
}
