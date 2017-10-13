package SocialNetwork.model;

public class Post {
	private int postId;
	private String content;
	private User postedBy;
	
	public Post(String content, User postedBy) {
		this.content = content;
		this.postedBy = postedBy;
	}

	public Post(int postId, String content, User postedBy) {
		this(content,postedBy);
		this.postId = postId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		if(postId != 0)
			this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(content != null)
			this.content = content;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		if(postedBy != null)
			this.postedBy = postedBy;
	}
	
	
}
