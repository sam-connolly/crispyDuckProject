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
	 * function I have made for testing purposes
	 */
	public void printDetails() {
		for (Task currentTask : taskList) {
			System.out.println(currentTask.getTaskID());
			System.out.println(currentTask.getTaskName());
			System.out.println(currentTask.getCaretaker());
		}
	}
	
	public int getSize() {
		return taskList.size();
	}
	
	public Task retrieveActiveTask(int index) {
		return taskList.get(index);
	}
}
