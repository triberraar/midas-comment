package be.tribersoft.midas.comment.service.api;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.domain.api.CommentRequest;

public interface CommentService {

	Comment create(CommentRequest commentRequest);

	Comment update(Long id, Long version, CommentRequest commentRequest);

	void delete(Long id, Long version);
}
