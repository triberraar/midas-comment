package be.tribersoft.sigma.comment.domain.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Mandatory field missing")
public class MandatoryFieldException extends RuntimeException {

}
