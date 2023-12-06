package webproje.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import webproje.app.entities.User;
import webproje.app.repos.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public User saveOneUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	

	//custom exception
	public User getOneUserById(@PathVariable Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}else return null;
	}
	
	public void deleteOneUser(@PathVariable Long userId) {
		userRepository.deleteById(userId);
	}

	public User getOneUserByName(String userName) {
		return userRepository.findByUserName(userName);
	}
	

}
