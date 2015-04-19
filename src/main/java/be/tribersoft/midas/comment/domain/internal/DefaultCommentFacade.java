package be.tribersoft.midas.comment.domain.internal;

import javax.inject.Inject;
import javax.inject.Named;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.domain.api.CommentFacade;
import be.tribersoft.midas.comment.domain.api.CommentRequest;

@Named
public class DefaultCommentFacade implements CommentFacade {

	@Inject
	private CommentFactory commentFactory;

	@Inject
	private DefaultCommentRepository commentRepository;

	@Override
	public Comment create(CommentRequest commentRequest) {
		DefaultComment comment = commentFactory.create(commentRequest);
		commentRepository.save(comment);
		return comment;
	}

	@Override
	public Comment update(Long id, Long version, CommentRequest commentRequest) {
		DefaultComment comment = commentRepository.getByIdAndVersion(id, version);
		comment.setContent(commentRequest.getContent());
		return comment;
	}

	@Override
	public void delete(Long id, Long version) {
		commentRepository.delete(id, version);

	}

}
