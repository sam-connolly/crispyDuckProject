/*
 * Class to store all ActiveTasks in an ArrayList, with various functions 
 * to use the data
 */
import java.util.ArrayList;

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
	 * has been assigned
	 * 
	 * @pram useruser to check
	 * @param taskCat the task category to count
	 * @return the number of tasks in the category they have been assigned
	 */
	public int numTasksInCat(String user, String taskCat) {
		// number assigned
		int numAssigned = 0;
		
		// loop through all tasks
		for (Task taskChecking : taskList) {
			// check if user and taskCat match
			if ((user == taskChecking.getCaretaker()) && (taskCat == taskChecking.getTaskCat())) {
				// increment count
				numAssigned = numAssigned + 1;
			} // if 
		} // for
		
		return numAssigned;
	} // function
	
	/*
	 * function I have made for testing purposes
	 */
	public void printDetails() {
		for (Task currentTask : taskList) {
			//currentTask.activateTask();
			System.out.println(currentTask.getTaskName());
			System.out.println(currentTask.getCaretaker());
			System.out.println(currentTask.getDateIssued());
		}
	}
	
	public int getSize() {
		return taskList.size();
	}
	
	public Task retrieveActiveTask(int index) {
		return taskList.get(index);
	}
}
