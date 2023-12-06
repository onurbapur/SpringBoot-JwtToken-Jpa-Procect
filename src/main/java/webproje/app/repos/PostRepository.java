package webproje.app.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import webproje.app.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByUserId(Long userId);
}
