package be.tribersoft.midas.comment.domain.api;

public interface CommentFacade {

	Comment create(CommentRequest commentRequest);

	Comment update(Long id, Long version, CommentRequest commentRequest);

	void delete(Long id, Long version);
}
