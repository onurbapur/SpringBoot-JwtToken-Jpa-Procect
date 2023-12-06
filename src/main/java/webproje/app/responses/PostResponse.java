package webproje.app.responses;

import lombok.Data;
import webproje.app.entities.Post;

@Data
public class PostResponse {
	private Long id;
	private Long userId;
	private String userName;
	private String title;
	private String text;
	
	public PostResponse(Post post) { //Bu bir constructor base mapper. bir mapper yapmış olduk
		this.id = post.getId();
		this.userId = post.getUser().getId();
		this.userName = post.getUser().getUserName();
		this.title = post.getTitle();
		this.text = post.getText();
	}
}
