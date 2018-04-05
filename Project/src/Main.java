import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
	Database database = new Database();

	ActiveTaskList allActiveTasks = new ActiveTaskList();
	
	allActiveTasks = database.getActiveTasks();
	
	allActiveTasks.printDetails();
	}

}
