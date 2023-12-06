package webproje.app.responses;

import lombok.Data;
import webproje.app.entities.Comment;

@Data
public class CommentResponse {
	private Long id;
	private Long userId;
	private String userName;
	private String text;
	
	public CommentResponse(Comment comment) { //Bu bir constructor base mapper. bir mapper yapmış olduk
		this.id = comment.getId();
		this.userId = comment.getUser().getId();
		this.userName = comment.getUser().getUserName();
		this.text = comment.getText();
	}

}
