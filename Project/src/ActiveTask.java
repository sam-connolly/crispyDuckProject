/*
 *  class stores information pertaining to tasks which are currently active
 *  (Have been assigned, are due to be assigned or are on hold)
 */
public class ActiveTask {
	// information on task
	private int taskID; 
	private String caretaker;
	private boolean completed; 
	private int dateIssued;
	private int dateDue;
	private String issueDesc; 
	private boolean signedOff;
	private int timeTaken;
	
	// constructor takes arguments for all data
	public ActiveTask(int taskID, String caretaker, boolean completed, int timeTaken, String issueDesc, boolean signedOff) {
		this.taskID = taskID;
		this.caretaker = caretaker;
		this.completed = completed;
		this.timeTaken = timeTaken;
		this.issueDesc = issueDesc;
		this.signedOff = signedOff;
	}
	
	
	public int getTaskID() {
		return taskID;
	}
	
	public String getCaretaker() {
		return caretaker;
	}
	
	public int getDateIssued() {
		return dateIssued;
	}
	
	public int getDateDue() {
		return dateDue;
	}
	
	public String getIssueDesc() {
		return issueDesc;
	}
	
	public boolean getSignedOff() {
		return signedOff;
	}
	
	public int getTimeTaken() {
		return timeTaken;
	}
	
	public void setCaretaker(String caretaker) {
		caretaker = this.caretaker;
	}
	
	public void setDateIssued(int dateIssued) {
		dateIssued = this.dateIssued;
	}
	
	public void setDateDue(int dateDue) {
		dateDue = this.dateDue;
	}
	
	public void setIssueDesc(String issueDesc) {
		issueDesc = this.issueDesc; 
	}
	
	public void setSignedOff(boolean signedOff) {
		signedOff = this.signedOff;
	}
	
	public String toString() {
		String isCompleted;
		
		if (completed) {
			isCompleted = "completed";
		}
		else {
			isCompleted = "not completed";
		}
		String returnString = ("Task No. " + taskID + ". Caretaker: " + caretaker + ". Issued on: " + dateIssued + 
				". Due for: " + dateDue + ". Is Currently: " + isCompleted);
		
		return returnString;
	}
	
}
