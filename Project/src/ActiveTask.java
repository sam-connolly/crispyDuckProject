import java.util.Date;

/*
 *  class stores information pertaining to tasks which are currently active
 *  (Have been assigned, are due to be assigned or are on hold)
 */
public class ActiveTask extends Task{
	// information on task
	private String caretaker;
	private boolean completed; 
	private Date completedOn;
	private int timeTaken;
	private Date dateIssued;
	private Date dateDue;
	private String issueDesc; 
	private boolean signedOff;
	
	// constructor takes arguments for all data
	public ActiveTask(int taskID, String caretaker, boolean completed, Date completedOn, Date dateIssued, Date dateDue, int timeTaken,
			String issueDesc, boolean signedOff,String taskName, String taskDesc, String taskCat, String priority, int repeating,
			int timeEstimate, String location) {
		super(taskID, taskName, taskDesc, taskCat, priority,
			repeating, timeEstimate, location);
		this.caretaker = caretaker;
		this.completed = completed;
		this.completedOn = completedOn;
		this.dateIssued = dateIssued;
		this.dateDue = dateDue;
		this.issueDesc = issueDesc;
		this.signedOff = signedOff;
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
