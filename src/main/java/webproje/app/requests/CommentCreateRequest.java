package webproje.app.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {

	private String text;
	private Long postId;
	private Long userId;
}
