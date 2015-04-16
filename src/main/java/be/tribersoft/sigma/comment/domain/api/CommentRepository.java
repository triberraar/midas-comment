package be.tribersoft.sigma.comment.domain.api;

import java.util.List;

public interface CommentRepository {

	Comment getById(Long id);

	List<? extends Comment> findAll();

}
