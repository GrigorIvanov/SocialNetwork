package SocialNetwork.DAO;

import java.util.List;

import SocialNetwork.model.Post;

public interface IPostDao {
	void addPost(Post post);

	void removePost(int postId);

	Post getPostById(int postId);
	
	List <Post> getAllPosts ();
}
