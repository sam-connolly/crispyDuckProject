import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
	Database database = new Database();

	TaskList allActiveTasks = new TaskList();
	
	allActiveTasks = database.getTasks();
	
	allActiveTasks.printDetails();
	}

}
