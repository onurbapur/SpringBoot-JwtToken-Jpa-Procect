package webproje.app.requests;

import lombok.Data;

@Data
public class PostCreateRequst {
	String text;
	String title;
	Long userId;

}
