package SocialNetwork.model;

import java.util.List;

public interface IPostDao {
	void addPost(Post post);

	void removePost(int postId);

	Post getPostById(int postId);
	
	List <Post> getAllPosts ();
}
