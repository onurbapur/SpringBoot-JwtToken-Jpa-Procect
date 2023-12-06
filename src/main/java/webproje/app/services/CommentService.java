package webproje.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import webproje.app.entities.Comment;
import webproje.app.entities.Post;
import webproje.app.entities.User;
import webproje.app.repos.CommentRepository;
import webproje.app.repos.PostRepository;
import webproje.app.requests.CommentCreateRequest;
import webproje.app.requests.CommentUpdateRequest;
import webproje.app.responses.CommentResponse;

@Service
public class CommentService {
	private CommentRepository commentRepository;
	private UserService userService;
	private PostRepository postRepository;

	public CommentService(CommentRepository commentRepository, UserService userService, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postRepository = postRepository;
	}

	public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
		
		List<CommentResponse> responselist = new ArrayList<>();
		List<Comment> list = new ArrayList<>();
		
		if(userId.isPresent() && postId.isPresent())
			list  = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
		else if(userId.isPresent())
			list  = commentRepository.findByUserId(userId.get());
		else if(postId.isPresent())
			list  = commentRepository.findByPostId(postId.get());
		else
			list  = commentRepository.findAll();
		
		for (Comment comment : list) {
			responselist.add(new CommentResponse(comment));
		}
		
		return responselist;
	}
	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentCreateRequest newCommentRequest) {
		User user = userService.getOneUserById(newCommentRequest.getUserId());
		Post post = postRepository.findById((newCommentRequest.getPostId())).orElse(null);
		if (post == null)
			return null;
		Comment newComment = new Comment();
		newComment.setText(newCommentRequest.getText());
		newComment.setPost(post);
		newComment.setUser(user);
		commentRepository.save(newComment);
		return newComment;
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest updateCommentRequest) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isPresent()) {
			Comment toUpdate = comment.get();
			toUpdate.setText(updateCommentRequest.getText());
			commentRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}
