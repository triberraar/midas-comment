package be.tribersoft.sigma.comment.domain.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violated")
public class OptimisticLockingException extends RuntimeException {

}
