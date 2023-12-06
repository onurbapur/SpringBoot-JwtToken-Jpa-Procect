package webproje.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import webproje.app.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
