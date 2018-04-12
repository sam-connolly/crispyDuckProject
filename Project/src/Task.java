import java.util.Date;

public class Task {
	private int taskID; 
	private String taskName; 
	private String taskDesc;
	private String taskCat;
	private String priority;
	private int repeating;
	private int timeEstimate;
	private String location;
	
	public Task(int taskID, String taskName, String taskDesc, String taskCat, String priority, int repeating,
			int timeEstimate, String location) {
		this.taskID = taskID;
		this.taskName = taskName;
		this.taskDesc = taskDesc;
		this.taskCat = taskCat;
		this.priority = priority;
		this.repeating = repeating;
		this.timeEstimate = timeEstimate;
		this.location = location;
	}
	
	public int getTaskID() {
		return taskID;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public String getTaskDesc() {
		return taskDesc;
	}
	
	public String getTaskCat() {
		return taskCat;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getRepeating() {
		return repeating;
	}
	
	public int getTimeEstimate() {
		return timeEstimate;
	}
	
	public String getCaretaker() {
		return null;
	}
}
