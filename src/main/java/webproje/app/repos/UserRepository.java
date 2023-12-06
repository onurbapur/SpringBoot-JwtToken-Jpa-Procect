package webproje.app.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import webproje.app.entities.User;
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
