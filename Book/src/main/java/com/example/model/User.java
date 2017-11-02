package com.example.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.example.exceptions.InvalidDataException;

public class User {
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", friendlist=" + friendlist + ", chat=" + chat + ", posts=" + posts
				+ ", dt=" + dt + ", sdf=" + sdf + ", birthDate=" + birthDate + "]";
	}

	private int userId;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Email
	private String email;
	@NotBlank
	private String password;
	
	private List<User> friendlist = Collections.synchronizedList(new ArrayList<User>());
	private List<Conversation> chat = Collections.synchronizedList(new ArrayList<Conversation>());
	private List<Post> posts = Collections.synchronizedList(new ArrayList<Post>());

	public List<Post> getPosts() {
		return posts;
	}

	public List<User> getFriendlist() {
		return friendlist;
	}
	
	public List<Conversation> getChat() {
		return chat;
	}
	
	java.util.Date dt = new java.util.Date();
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String birthDate = sdf.format(dt);

	public User(String firstName, String lastName, String email, String birthDate, String password) throws InvalidDataException {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthDate(birthDate);
		this.setEmail(email);
		this.setPassword(password);
	}

	public User(int id, String firstName, String lastName, String email, String birthDate, String password) throws InvalidDataException {
		this(firstName, lastName, email, birthDate, password);
		this.setUserId(id);
	}
	
	public User() {
		
	}

	public int getUserId() {
		return userId;
	}
	
	
	public void setUserId(int id) {
		if (isNotNull(id)) {
			this.userId = id;
		}
	}


	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		try {
			if (isNotNull(firstName)) {
				this.firstName = firstName;
			} else {
				throw new InvalidDataException("The given name is invalid");
			}
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		try {
			if (isNotNull(lastName)) {
				this.lastName = lastName;
			} else {
				throw new InvalidDataException("The given name is invalid");
			}
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidDataException {
			if (isValidEmail(email)) {
				this.email = email;
			} else {
				 throw new InvalidDataException("The given email is invalid");
			}
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		try{
			if (isNotNull(birthDate)) {
				this.birthDate = birthDate;
			} else {
				throw new InvalidDataException("The given birthdate is invalid");
			}
		}catch(InvalidDataException e){
			e.printStackTrace();
		}
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		if (isNotNull(password)) {
			this.password = password;
		}
	}

	public boolean isNotNull(Object o) {
		if (!o.equals(null)&&o.toString().trim().length()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isValidEmail(String email){
		if(isNotNull(email) && email.contains("@")){
			return true;
		}
		return false;
	}
	
}
