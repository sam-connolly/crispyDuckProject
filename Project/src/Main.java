import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) {
		
	Database database = new Database();

	TaskList allTasks = new TaskList();
	
	UserList allUsers = new UserList();
	
	allTasks = database.getTasks();
	allUsers = database.getUsers();
	
	//allTasks.printDetails();
	allTasks.allocateTasks(allUsers, allTasks);
	
	// allTasks.numTasksInCat("sdean", "Interior Repair");
	}

}
