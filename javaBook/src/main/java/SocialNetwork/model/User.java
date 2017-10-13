package SocialNetwork.model;

public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String email;

	java.util.Date dt = new java.util.Date();
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String birthDate = sdf.format(dt);
	
	public User(String firstName, String lastName, String email, String birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
	}
	
	
	
	public User(int userId, String firstName, String lastName, String email, String birthDate) {
		this(firstName,lastName,email,birthDate);
		this.userId = userId;
		
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
		if (firstName != null){
			this.firstName = firstName;
		}else {
			//throw new InvalidDataException("The given name is invalid")
		}
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		if(lastName != null){
			this.lastName = lastName;
		}else{
			//throw new InvalidDataException("The given name is invalid")
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		if(email != null){
			this.email = email;
		}else{
			//throw new InvalidDataException("The given email is invalid")
		}
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		if(birthDate!= null){			
			this.birthDate = birthDate;
		}else{
			//throw new InvalidDataException("The given birthdate is invalid")
		}
	}

	
}
