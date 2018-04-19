/*
 * Class to store all ActiveTasks in an ArrayList, with various functions 
 * to use the data
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskList {
	private ArrayList<Task> taskList;
	
	public TaskList() {
		taskList = new ArrayList<Task>();
	}
	
	/*
	 * add a task to the list
	 * @param the task to add
	 */
	public void addTask(Task taskToAdd) {
		taskList.add(taskToAdd);
	}
	
	/*
	 * get the number of tasks of a specified category the specified user 
	 * has been assigned. If they haven't been assigned any then return 1.
	 * This is because the calculation to determine an assignment rating will do
	 * preference level / number assigned. Therefore number assigned should'nt be 0.
	 * 
	 * @pram user user to check
	 * @param taskCat the task category to count
	 * @return the number of tasks in the category they have been assigned
	 */
	public int numTasksInCat(String user, String taskCat) {
		// number assigned
		int numAssigned = 1;
		
		// loop through all tasks
		for (Task taskChecking : taskList) {
			// check if user and taskCat match
			if ((taskChecking.getCaretaker().equals(user)) && (taskChecking.getTaskCat().equals(taskCat))) {
				// increment count
				numAssigned = numAssigned + 1;
			} // if 
		} // for
		
		// System.out.println(user + " " + taskCat + " " + numAssigned);
		return numAssigned;
	} // function
	
	/*
	 * loops through list of tasks. If the task is not assigned calls the allocateTask() function
	 */
	public void allocateTasks(UserList allUsers, TaskList allTasks) {
		// loop through all tasks
		for (Task taskToCheck : taskList) {
			// if task is not assigned
			if (taskToCheck.getCaretaker().equals("Not Assigned")) {
				// allocate the task
				taskToCheck.allocateTask(allUsers, allTasks);
			} // if 
		} // for 
	} // function
	
	public void checkIfDueAllocation(Task taskToCheck) {
		  // new date format
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		  Date currentDate = new Date();
		  // String taskLastCompleted = taskToCheck.getLastCompleted;
	}
	
	
	/*
	 * function I have made for testing purposes
	 */
	public void printDetails() {
		for (Task currentTask : taskList) {
			System.out.println(currentTask.getTaskName());
			System.out.println(currentTask.getCaretaker());
			System.out.println(currentTask.getDateIssued());
			System.out.println(currentTask.getTaskCat());
			
			System.out.println();
		}
	}
	
	public int getSize() {
		return taskList.size();
	}
	
	public Task retrieveActiveTask(int index) {
		return taskList.get(index);
	}
}
