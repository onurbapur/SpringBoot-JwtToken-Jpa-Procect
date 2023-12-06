package webproje.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import webproje.app.entities.Post;
import webproje.app.entities.User;
import webproje.app.repos.PostRepository;
import webproje.app.requests.PostCreateRequst;
import webproje.app.requests.PostUpdateRequest;
import webproje.app.responses.PostResponse;
//import java.util.stream.Collectors;
@Service
public class PostService {

	private PostRepository postRepository;
	private UserService userService;

	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list ;
		List<PostResponse> listResponse = new ArrayList<PostResponse>();
		if (userId.isPresent()) {
			list = postRepository.findByUserId(userId.get());
		}else
			list = postRepository.findAll();
		
		for (Post post : list) {      
			listResponse.add(new PostResponse(post));
		}
		//listeyi mapleme. post listesinin her bir elemanını postresponsa çevirip postresponse listesini oluşturuyor
		//list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList()); 
		return listResponse;		
	}
	public PostResponse getOnePostById(Long postId) {
		return new PostResponse(postRepository.findById(postId).orElse(null));	 
	}
	public Post createOnePost(PostCreateRequst newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if (user == null)
			return null;
		Post newPost = new Post();
		newPost.setText(newPostRequest.getText());
		newPost.setTitle(newPostRequest.getTitle());
		newPost.setUser(user);
		return postRepository.save(newPost);
	}
	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(updatePost.getText());
			toUpdate.setTitle(updatePost.getTitle());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}
	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
	}
}