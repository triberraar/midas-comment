package be.tribersoft.midas.comment.domain.internal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<DefaultComment, Long> {

}
