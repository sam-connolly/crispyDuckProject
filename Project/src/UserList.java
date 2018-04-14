import java.util.ArrayList;

public class UserList {
	private ArrayList<User> allUsers;
	
	public UserList() {
		allUsers = new ArrayList<User>();
	}
	
	public void addUser(User userToAdd) {
		allUsers.add(userToAdd);
	}
	
	public void removeUser(int index) {
		allUsers.remove(index); 
		
		// include call to function that will remove user from database
	}
	
	/*
	 * function I have made for testing purposes
	 */
	public void printDetails() {
		for (User currentUser : allUsers) {
			System.out.println(currentUser.getUsername());
			currentUser.printPreferenceInfo();
			System.out.println();
		}
	}
	
	
	public User getUser(int index) {
		return allUsers.get(index);
	}
}
