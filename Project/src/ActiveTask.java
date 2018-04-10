import java.util.Date;

/*
 *  class stores information pertaining to tasks which are currently active
 *  (Have been assigned, are due to be assigned or are on hold)
 */
public class ActiveTask {
	// information on task
	private int taskID; 
	private String caretaker;
	private boolean completed; 
	private Date completedOn;
	private Date dateIssued;
	private Date dateDue;
	private String issueDesc; 
	private boolean signedOff;
	private int timeTaken;
	private String taskName; 
	private String taskDesc;
	private String priority;
	private int timeEstimate;
	private String location;
	
	// constructor takes arguments for all data
	public ActiveTask(int taskID, String caretaker, boolean completed, Date completedOn, Date dateIssued, Date dateDue, int timeTaken,
			String issueDesc, boolean signedOff,String taskName, String taskDesc, String priority,
			int timeEstimate, String location) {
		this.taskID = taskID;
		this.caretaker = caretaker;
		this.completed = completed;
		this.completedOn = completedOn;
		this.dateIssued = dateIssued;
		this.dateDue = dateDue;
		this.timeTaken = timeTaken;
		this.issueDesc = issueDesc;
		this.signedOff = signedOff;
		this.taskName = taskName;
		this.taskDesc = taskDesc;
		this.priority = priority;
		this.timeEstimate = timeEstimate;
		this.location = location;
	}
	
	
	public int getTaskID() {
		return taskID;
	}
	
	public String getCaretaker() {
		return caretaker;
	}
	
	public Date getDateIssued() {
		return dateIssued;
	}
	
	public Date getDateDue() {
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
	
	public void setDateIssued(Date dateIssued) {
		dateIssued = this.dateIssued;
	}
	
	public void setDateDue(Date dateDue) {
		dateDue = this.dateDue;
	}
	
	public void setIssueDesc(String issueDesc) {
		issueDesc = this.issueDesc; 
	}
	
	public void setSignedOff(boolean signedOff) {
		signedOff = this.signedOff;
	}
	
	public String toString() {
		return null;
	}
	
}
