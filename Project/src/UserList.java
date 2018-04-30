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
	
	/* 
	 * function to find a user to allocate a task to 
	 * 
	 * @param taskCat the category of the task being allocated
	 * @param allTasks a list of all tasks
	 * 
	 * @return a list of eligible users for allocation
	 */
	public ArrayList<User> findToAssign(String taskCat, TaskList allTasks) {
		// create variable for the highest eligibility rating
		float highestRating = 0;
		// list of eligible users to return
		ArrayList<User> eligibleUsers = new ArrayList<User>();
		
		// loop through all users
		for (User userChecking : allUsers) {
			// calculate an assignment rating for this user
			float assignmentRating = calcAssignmentRating(userChecking, taskCat, allTasks);
			
			// if the assignment rating is higher than the current highest rating found
			if(assignmentRating > highestRating) {
				// make this the new highest rating
				highestRating = assignmentRating;
			} // if
		} // for
		
		System.out.println();
		
		// loop through all users
		for (User userChecking : allUsers) {
			// calculate an assignment rating for this user
			float assignmentRating = calcAssignmentRating(userChecking, taskCat, allTasks);
			
			// if the assignment rating is equal to the highest found
			if(assignmentRating == highestRating) {
				// add this user to the list of eligible users
				eligibleUsers.add(userChecking);
			}
		}
		return eligibleUsers;
	}

	/*
	 * helper for findToAssign()
	 * 
	 * @param user the user whos rating should be determined
	 * @param taskCat the category of task to determine the rating for
	 * @param allTasks list of all tasks
	 * 
	 * @return an assignment rating
	 */
	public float calcAssignmentRating(User user, String taskCat, TaskList allTasks) {
		float assignmentRating = 0;
		// retrieve number of tasks in this category already assigned to this user
		int numAlreadyAssigned = allTasks.numTasksInCat(user.getUsername(), taskCat);
		int userPreferenceLevel = user.getPreferenceLevel(taskCat);
		
		
		assignmentRating = (float) userPreferenceLevel / (float) numAlreadyAssigned;
		
		return assignmentRating;
	}
	
	public User getUser(int index) {
		return allUsers.get(index);
	}
	
	public User getUserCaretaker(String username) {
		for (User userChecking : allUsers) {
			if (userChecking.getUsername().equals(username)) {
				return userChecking;
			}
		}
		
		return null;
	}
}
