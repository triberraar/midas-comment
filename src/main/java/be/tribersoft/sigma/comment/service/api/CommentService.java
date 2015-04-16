package be.tribersoft.sigma.comment.service.api;

import be.tribersoft.sigma.comment.domain.api.Comment;
import be.tribersoft.sigma.comment.domain.api.CommentRequest;

public interface CommentService {

	Comment create(CommentRequest commentRequest);

	Comment update(Long id, Long version, CommentRequest commentRequest);
}
