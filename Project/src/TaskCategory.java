/**
 * class stores information from CaretakerCategory table
 * @author Jesse
 *
 */
public class TaskCategory {
	private String taskCategory;
	private int efficiency;
	private int preferenceLevel;
	private int numberCompleted;
	
	public TaskCategory(String taskCategory, int efficiency, int preferenceLevel, int numberCompleted) {
		this.taskCategory = taskCategory;
		this.efficiency = efficiency;
		this.preferenceLevel = preferenceLevel;
		this.numberCompleted = numberCompleted;
	}
	
	public String getTaskCategory() {
		return taskCategory;
	}
	
	public int getEfficiency() {
		return efficiency;
	}
	
	public int getPreferenceLevel() {
		return preferenceLevel;
	}
	
	public int getNumberCompleted() {
		return numberCompleted;
	}
	
	public void setNumberCompleted(int newVal) {
		numberCompleted = newVal;
	}
	
}
