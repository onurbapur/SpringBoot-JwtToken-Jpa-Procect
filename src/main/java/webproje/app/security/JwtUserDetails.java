package webproje.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import webproje.app.entities.User;

@Data
public class JwtUserDetails implements UserDetails{  

	//bu class userların (giriş yapacakların sadece username ya da email ve passwordunu tutar.
	//kontrol etmek için girişi bu classı kullanıyoruz. normal classta user a ait daha fazla bilgi bulunuyor.) 
	//Authtentication için kullanılacak class
	
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities; 
	//verilen tetkiler listesi admin/user/manager...

	private JwtUserDetails(Long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static JwtUserDetails create(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("user")); //user diye authority (yetki)oluşturup gelen usera ekleyip JwtUsera çevirip veriyoruz
		return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(), authorities);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true; 
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
