/*
 * Class to store all ActiveTasks in an ArrayList, with various functions 
 * to use the data
 */
import java.util.ArrayList;

public class ActiveTaskList {
	private ArrayList<ActiveTask> activeTaskList;
	
	public ActiveTaskList() {
		activeTaskList = new ArrayList<ActiveTask>();
	}
	
	/*
	 * add a task to the list
	 * @param the task to add
	 */
	public void addTask(ActiveTask taskToAdd) {
		activeTaskList.add(taskToAdd);
	}
	
	/*
	 * function I have made for testing purposes
	 */
	public void printDetails() {
		for (ActiveTask activeTask : activeTaskList) {
			System.out.println(activeTask.toString());
		}
	}
	
	public int getSize() {
		return activeTaskList.size();
	}
	
	public ActiveTask retrieveActiveTask(int index) {
		return activeTaskList.get(index);
	}
}
