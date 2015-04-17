package be.tribersoft.midas.comment.domain.internal;

import javax.inject.Named;

import be.tribersoft.midas.comment.domain.api.CommentRequest;

@Named
public class CommentFactory {

	public DefaultComment create(CommentRequest commentRequest) {
		return new DefaultComment(commentRequest.getContent());
	}

}
