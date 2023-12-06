package webproje.app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import webproje.app.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByUserId(Long userId);
	List<Comment> findByPostId(Long postId);
	List<Comment> findByUserIdAndPostId(Long userId, Long postId);
}