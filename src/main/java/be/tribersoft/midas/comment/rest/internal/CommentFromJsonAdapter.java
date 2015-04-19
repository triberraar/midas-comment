package be.tribersoft.midas.comment.rest.internal;

import be.tribersoft.midas.comment.domain.api.CommentRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "id", "version" })
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
