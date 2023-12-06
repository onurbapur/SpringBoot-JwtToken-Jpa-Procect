package webproje.app.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="posts")
@Data
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER) //postu çektiğimde userı getirme
	@JoinColumn(name="user_id", nullable=false) //burdaki user post tablosunda user_id ye denk geliyor
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	private String title;
	@Lob
	@Column(columnDefinition = "text")
	private String text;
}