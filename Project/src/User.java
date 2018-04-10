
public class User {
	private String username;
	private String passwordHash;
	private boolean admin;
	private String fName;
	private String sName;
	
	public User(String username, String passwordHash, boolean admin, String fName, String sName) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.admin = admin;
		this.fName = fName;
		this.sName = sName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public boolean getAdmin() {
		return admin;
	}
	
	public String getFirstName() {
		return fName;
	}
	
	public String getSurname() {
		return sName;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public void setFirstName(String fName) {
		this.fName = fName; 
	}
	
	public void setSurname(String sName) {
		this.sName = sName; 
	}
	
	public String toString() {
		String isAdmin;
		if (admin) {
			isAdmin = "is a manager";
		}
		else { 
			isAdmin = "is a caretaker";
		}
		String returnString = ("Username: " + username + ". " + fName + " " + sName + " " + isAdmin);
		
		return returnString;
	}
}
