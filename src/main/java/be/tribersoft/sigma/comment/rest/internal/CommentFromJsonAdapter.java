package be.tribersoft.sigma.comment.rest.internal;

import be.tribersoft.sigma.comment.domain.api.CommentRequest;

public class CommentFromJsonAdapter implements CommentRequest {

	private String content;

	@Override
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
