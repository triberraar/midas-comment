package be.tribersoft.midas.comment.domain.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.util.StringUtils;

import be.tribersoft.midas.comment.domain.api.Comment;
import be.tribersoft.midas.comment.domain.api.exception.MandatoryFieldException;

@Entity(name = "comment")
public class DefaultComment implements Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	public Long version;
	@Column(nullable = false)
	private String content;

	public DefaultComment() {
	}

	public DefaultComment(String content) {
		if (StringUtils.isEmpty(content)) {
			throw new MandatoryFieldException();
		}
		this.content = content;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
