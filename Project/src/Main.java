import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) {
		  int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
		  
		  System.out.println(randomNum);
	/*Database database = new Database();

	TaskList allActiveTasks = new TaskList();
	
	UserList allUsers = new UserList();
	
	allActiveTasks = database.getTasks();
	allUsers = database.getUsers();
	
	// allActiveTasks.printDetails();
	allUsers.printDetails();*/
	
	
	}

}
