import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

/**
 * store information on Users
 * @author Jesse
 *
 */
public class User {
	private String username;
	private String passwordHash;
	private boolean admin;
	private String fName;
	private String sName;
	ArrayList<TaskCategory> preferences;
	
	public User(String username, String passwordHash, boolean admin, String fName, String sName) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.admin = admin;
		this.fName = fName;
		this.sName = sName;
	}
	
	/*
	 * accept a list of TaskCategory objects containing preferences associated with the user
	 * 
	 * @param preferences the list of the users preferences
	 */
	public void setPreferences(ArrayList<TaskCategory> preferences) {
		// assign the user their preferences
		this.preferences = preferences;
	}
	
	/*
	 * increment the number of task in a category this user has completed
	 * 
	 * @param the category of the count to increment
	 */
	public void incrementNumCompleted(String taskCat) {
		// loop through all categories
		for (TaskCategory checkTask : preferences) {
			// if the current category is the one we want to increment
			if (checkTask.getTaskCategory().equals(taskCat)) {
				// get the number completed and increment it
				int newVal = checkTask.getNumberCompleted() + 1;
				// set the number completed in that object to the new value
				checkTask.setNumberCompleted(newVal);
			} // if
		} // for
	} // function
	public void printPreferenceInfo() {
		for (TaskCategory category : preferences) {
			System.out.println(category.getTaskCategory());
			System.out.println("Preference: " + category.getPreferenceLevel());
			System.out.println("Efficiency: " + category.getEfficiency());
			System.out.println("Number completed: " + 	category.getNumberCompleted());
		} // for
	} // fucntion
	
	/*
	 * get the users preference level for a category of task
	 * 
	 * @param taskCat the category we want to check 
	 * 
	 * @return the users preference level for specified category of task
	 */
	public int getPreferenceLevel(String taskCat) {
		int preferenceLevel = 0;
		// loop through all categories
		for (TaskCategory checkTask : preferences) {
			// if the category is the one we want to check
			if (checkTask.getTaskCategory().equals(taskCat)) {
				// get the users preference level
				preferenceLevel = checkTask.getPreferenceLevel();
			} // if
		} // for 
		
		return preferenceLevel;
	} // function
	
	public void updateEfficiency(String taskCat, float efficiencyForTask) throws SQLException {
		for (TaskCategory checkTask : preferences) {
			if (checkTask.getTaskCategory().equals(taskCat)) {
				checkTask.updateEfficiency(efficiencyForTask);
				
				Database db = new Database();
				db.updateEfficiency(taskCat, username, checkTask.getEfficiency(), checkTask.getNumberCompleted());
			} // if
		} // for 
	} // function
	
	public DefaultTableModel getPreferenceModel(DefaultTableModel prefModel, TaskList allTasks, String username) {
		Object[] row = new Object[5];
		for (TaskCategory catForRow : preferences) {
			row[0] = catForRow.getTaskCategory();
			row[1] = catForRow.getPreferenceLevel();
			row[2] = catForRow.getEfficiency();
			row[3] = allTasks.numTasksInCat(username, catForRow.getTaskCategory());
			row[4] = catForRow.getNumberCompleted();
			
			prefModel.addRow(row);
		}
		return prefModel;
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
	
	public String getFullName() {
		return fName + " " + sName;
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
