package be.tribersoft.sigma.comment.service.internal;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import be.tribersoft.sigma.comment.domain.api.Comment;
import be.tribersoft.sigma.comment.domain.api.CommentFacade;
import be.tribersoft.sigma.comment.domain.api.CommentRequest;
import be.tribersoft.sigma.comment.service.api.CommentService;

@Named
@Transactional
public class DefaultCommentService implements CommentService {

	@Inject
	private CommentFacade commentFacade;

	@Override
	public Comment create(CommentRequest commentRequest) {
		return commentFacade.create(commentRequest);
	}

	@Override
	public Comment update(Long id, Long version, CommentRequest commentRequest) {
		return commentFacade.update(id, version, commentRequest);
	}

}
