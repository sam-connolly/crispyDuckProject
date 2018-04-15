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
	
	public ArrayList<User> findToAssign(String taskCat, TaskList allTasks) {
		float highestRating = 0;
		ArrayList<User> eligibleUsers = new ArrayList<User>();
		
		for (User userChecking : allUsers) {
			float assignmentRating = calcAssignmentRating(userChecking, taskCat, allTasks);
			
			if(assignmentRating > highestRating) {
				highestRating = assignmentRating;
			}
		}
		
		for (User userChecking : allUsers) {
			float assignmentRating = calcAssignmentRating(userChecking, taskCat, allTasks);
			
			if(assignmentRating == highestRating) {
				eligibleUsers.add(userChecking);
			}
		}
		return eligibleUsers;
	}

	/*
	 * helper for findToAssign()
	 */
	public float calcAssignmentRating(User user, String taskCat, TaskList allTasks) {
		float assignmentRating = 0;
		int numAlreadyAssigned = allTasks.numTasksInCat(user.getUsername(), taskCat);
		int userPreferenceLevel = user.getPreferenceLevel(taskCat);
		
		assignmentRating = (float) userPreferenceLevel / (float) numAlreadyAssigned;
		
		return assignmentRating;
	}
	
	public User getUser(int index) {
		return allUsers.get(index);
	}
}
