package SocialNetwork.model;

public class Comment {
	private Post commentedPost;
	private String content;
	private User commenter;
	
	public Comment(Post commentedPost, String content, User commenter) {
		super();
		this.commentedPost = commentedPost;
		this.content = content;
		this.commenter = commenter;
	}

	public Post getCommentedPost() {
		return commentedPost;
	}

	public void setCommentedPost(Post commentedPost) {
		if(commentedPost != null)
			this.commentedPost = commentedPost;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(content != null)
			this.content = content;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		if(commenter != null)
			this.commenter = commenter;
	}

	
}
