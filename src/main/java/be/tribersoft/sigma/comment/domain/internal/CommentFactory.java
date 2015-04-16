package be.tribersoft.sigma.comment.domain.internal;

import javax.inject.Named;

import be.tribersoft.sigma.comment.domain.api.CommentRequest;

@Named
public class CommentFactory {

	public DefaultComment create(CommentRequest commentRequest) {
		return new DefaultComment(commentRequest.getContent());
	}

}
