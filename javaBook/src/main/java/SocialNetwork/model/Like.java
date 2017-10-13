package SocialNetwork.model;

public class Like {
	private User userWhoLikedIt;
	private Post post;
	
	public Like(User userWhoLikedIt, Post post) {
		super();
		this.userWhoLikedIt = userWhoLikedIt;
		this.post = post;
	}

	public User getUserWhoLikedIt() {
		return userWhoLikedIt;
	}

	public void setUserWhoLikedIt(User userWhoLikedIt) {
		if(userWhoLikedIt != null)
			this.userWhoLikedIt = userWhoLikedIt;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		if(post != null)
			this.post = post;
	}
	
}
