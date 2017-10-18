package book.java.model;

public class Like {
	private User userWhoLikedIt;
	private Post post;
	
	public Like(User userWhoLikedIt, Post post) {
		this.setUserWhoLikedIt(userWhoLikedIt);
		this.setPost(post);
	}

	public User getUserWhoLikedIt() {
		return userWhoLikedIt;
	}

	public void setUserWhoLikedIt(User userWhoLikedIt) {
		if(isNotNull(userWhoLikedIt))
			this.userWhoLikedIt = userWhoLikedIt;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		if(isNotNull(post))
			this.post = post;
	}
	public boolean isNotNull(Object o) {
		if (o.equals(null)) {
			return false;
		} else {
			return true;
		}
	}
	
}
