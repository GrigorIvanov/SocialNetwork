package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.example.exceptions.InvalidDataException;

public class User {

	private int userId;
	@NotBlank
	@Length(max = 30)
	private String firstName;
	@NotBlank
	@Length(max = 30)
	private String lastName;
	@Email
	@Length(max = 30)
	private String email;
	@NotBlank
	@Length(min = 6, max = 30)
	private String password;
	private String profilPic;

	private List<User> friendlist = Collections.synchronizedList(new ArrayList<User>());
	private List<Conversation> conversations = Collections.synchronizedList(new ArrayList<Conversation>());
	private List<Post> posts = Collections.synchronizedList(new ArrayList<Post>());

	public void addFriend(User user) {
		if(user!=null) {
		this.friendlist.add(user);
		}
	}
	public void removeFriend(User user) {
		if(user!=null) {
		this.friendlist.remove(user);
		}
	}
	
	public List<User> getFriends(){
		return Collections.unmodifiableList(this.friendlist);
	}
	
	public void addConversation(Conversation conv) {
		if(conv!=null) {
		this.conversations.add(conv);
		}
	}
	public void removeConversation(Conversation conv) {
		if(conv!=null) {
		this.conversations.remove(conv);
		}
	}
	
	public List<Conversation> getConversations(){
		return Collections.unmodifiableList(this.conversations);
	}
	
	public void addPost(Post post) {
		if(post!=null) {
			this.posts.add(post);
		}
	}
	public void removePost(Post post) {
		if(post!=null) {
			this.conversations.remove(post);
		}
	}
	
	public List<Post> getPosts(){
		return Collections.unmodifiableList(this.posts);
	}

	

	// java.util.Date dt = new java.util.Date();
	// java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss");
	// String birthDate = sdf.format(dt);

	public User(String firstName, String lastName, String email, String password) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
		this.setProfilPic("avatar.jpeg");
	}

	public String getProfilPic() {
		return profilPic;
	}

	public void setProfilPic(String profilPic) {
		if (profilPic != null) {
			this.profilPic = profilPic;
		}
	}

	public User(String email, String password) throws InvalidDataException {
		this.setEmail(email);
		this.setPassword(password);
	}

	public User(int id, String firstName, String lastName, String email, String password) {
		this(firstName, lastName, email, password);
		this.setUserId(id);
	}

	public User() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {

		this.userId = id;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {

		this.email = email;

	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {

		this.password = password;

	}

	public boolean isValidEmail(String email) {
		if (email != null && email.contains("@")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", friendlist=" + friendlist + ", chat=" + conversations + ", posts=" + posts + "]";
	}

}
