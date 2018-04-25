import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) throws ParseException, SQLException {
		
	Database database = new Database();

	TaskList allTasks = new TaskList();
	
	UserList allUsers = new UserList();
	
	allTasks = database.getTasks();
	allUsers = database.getUsers();

	allTasks.testAllocate(allUsers, allTasks);
	allTasks.checkSignedOffDate();
	allTasks.printDetails();
	
	}

}
