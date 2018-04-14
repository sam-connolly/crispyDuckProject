import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
	Database database = new Database();

	TaskList allActiveTasks = new TaskList();
	
	UserList allUsers = new UserList();
	
	allActiveTasks = database.getTasks();
	allUsers = database.getUsers();
	
	// allActiveTasks.printDetails();
	allUsers.printDetails();
	
	
	}

}
