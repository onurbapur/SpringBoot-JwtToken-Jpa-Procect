package webproje.app.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="c_likes")
@Data
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY) //postu çektiğimde userı getirme
	@JoinColumn(name="user_id", nullable=false) //burdaki user comment tablosunda user_id ye denk geliyor
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY) //postu çektiğimde userı getirme
	@JoinColumn(name="comment_id", nullable=false) //burdaki user post tablosunda user_id ye denk geliyor
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Comment comment;

}
