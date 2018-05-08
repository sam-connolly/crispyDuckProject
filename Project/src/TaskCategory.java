/**
 * class stores information from CaretakerCategory table
 * @author Jesse
 *
 */
public class TaskCategory {
	private String taskCategory;
	private float efficiency;
	private int preferenceLevel;
	private int numberCompleted;
	
	public TaskCategory(String taskCategory, float efficiency, int preferenceLevel, int numberCompleted) {
		this.taskCategory = taskCategory;
		this.efficiency = efficiency;
		this.preferenceLevel = preferenceLevel;
		this.numberCompleted = numberCompleted;
	}
	
	public String getTaskCategory() {
		return taskCategory;
	}
	
	public float getEfficiency() {
		return efficiency;
	}
	
	public void updateEfficiency(float newRating) {
		numberCompleted++;
		efficiency = ( efficiency + newRating ) / (float) numberCompleted;
	}
	
	public void undoUpdateEfficiency(float ratingUndo) {
		efficiency = ( efficiency * (float) numberCompleted ) - ratingUndo;
		numberCompleted--;
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
