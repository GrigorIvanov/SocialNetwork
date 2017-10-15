package book.java.DAO;

import java.util.List;

import book.java.model.Post;


public interface IPostDAO {
	void addPost(Post post);

	void removePost(int postId);

	Post getPostById(int postId);
	
	List <Post> getAllPosts ();
}
