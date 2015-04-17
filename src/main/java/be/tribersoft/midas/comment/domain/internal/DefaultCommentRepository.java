package be.tribersoft.midas.comment.domain.internal;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import be.tribersoft.midas.comment.domain.api.CommentRepository;
import be.tribersoft.midas.comment.domain.api.exception.NotFoundException;
import be.tribersoft.midas.comment.domain.api.exception.OptimisticLockingException;

@Named
public class DefaultCommentRepository implements CommentRepository {

	@Inject
	private CommentJpaRepository commentJpaRepository;

	@Override
	public DefaultComment getById(Long id) {
		DefaultComment comment = commentJpaRepository.findOne(id);
		if (comment == null) {
			throw new NotFoundException();
		}
		return comment;
	}

	@Override
	public List<DefaultComment> findAll() {
		return commentJpaRepository.findAll();
	}

	public DefaultComment save(DefaultComment defaultComment) {
		return commentJpaRepository.save(defaultComment);
	}

	public DefaultComment getByIdAndVersion(Long id, Long version) {
		DefaultComment comment = getById(id);
		if (version != comment.getVersion()) {
			throw new OptimisticLockingException();
		}
		return comment;
	}

}
