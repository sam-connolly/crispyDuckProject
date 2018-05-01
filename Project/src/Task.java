import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
 
/**
 * stores information on a task
 * @author Jesse
 *
 */
public class Task {
  private int jobID;
  private int taskID; 
  private String taskName; 
  private String taskDesc;
  private String taskCat;
  private String priority;
  private int repeating;
  private int timeEstimate;
  private String location;
  private String firstAllocation;
  private String lastAllocated;
  private int timeGiven;
  
  private String caretaker;
  private boolean completed; 
  private int timeTaken;
  private String dateIssued;
  private String dateDue;
  private boolean issue;
  private String issueDesc; 
  private boolean signedOff;
  private String signedOffOn;
  // private String lastCompleted;
  
  // constructor takes a builder as parameter
  private Task(TaskBuilder builder) {
	jobID = builder.jobID;
    taskID = builder.taskID;
    taskName = builder.taskName;
    taskDesc = builder.taskDesc;
    taskCat = builder.taskCat;
    priority = builder.priority;
    repeating = builder.repeating;
    timeEstimate = builder.timeEstimate;
    location = builder.location;
    caretaker = builder.caretaker;
    completed = builder.completed;
    timeTaken = builder.timeTaken;
    dateIssued = builder.dateIssued;
    dateDue = builder.dateDue;
    issue = builder.issue;
    issueDesc = builder.issueDesc;
    signedOff = builder.signedOff;
    signedOffOn = builder.signedOffOn;
    firstAllocation = builder.firstAllocation;
    lastAllocated = builder.lastAllocated;
  }

  //getters
  public int getJobID() {
	  return jobID;
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
  
  public Integer getTimeEstimateInt()
  {
	  return timeEstimate;
  }
  
  public String getTimeEstimateString() { 
	   return timeEstimate/24/60 + " days, " + timeEstimate/60%24 + " hours, " + timeEstimate%60 + " minutes.";
	 }
  /*
   	public String lastCompleted() { 
   	  return lastCompleted
   	 }
   */
  
  /*
   *  getter for caretaker returns either the username of the caretaker if
   *  it's been allocated or "Not Assigned" if it hasn't. getCaretaker() cannot return null
   *  because it is used in a function that does a comparison using equals()
   */
  public String getCaretaker() {
	  // if there is a caretaker allocated to this task
	  if(caretaker != null) {
		  // return the username
		  return caretaker;
	  }
	  
	  // if not
	  else {
		  // return "not assigned"
		  return "Not Assigned";
	  }
  }
  
  public boolean getSignedOff() {
	  return signedOff;
  }
  
  public String getSignedOffOn() { 
	  return signedOffOn;
  }
  
  public String getDateIssued() {
	  return dateIssued;
  }
  
  public String getDateDue()
  {
	  return dateDue;
  }
  
  public String getLastAllocated() {
	  return lastAllocated;
  }
  
  public String getFirstAllocation() {
	  return firstAllocation;
  }
  
  public Boolean getCompleted()
  {
	  return completed;
  }
  
  public Boolean getIssue()
  {
	  return issue;
  }
  
  public String getIssueDesc()
  {
	  return issueDesc;
  }
  public void setLastAllocated(String lastAllocated) {
	  this.lastAllocated = lastAllocated;
  }
  
  
  
  /*
   * allocates the task to a caretaker. Sets the dateAllocated to today, 
   * calls the finToAssign function to get a list of eligible caretakers and 
   * randomly selects one of them to assign the task to.
   */
  public void allocateTask(UserList allUsers, TaskList allTasks) throws SQLException, ParseException {
	  Database db = new Database();
	  // new date format
	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  Date currentDate = new Date();
	  // set dateIssued and last allocated to today
	  dateIssued = dateFormat.format(currentDate);
	  lastAllocated = dateFormat.format(currentDate);
	  db.updateLastAllocated(taskID, lastAllocated);
	  allTasks.updateLastAllocation(taskID, lastAllocated);
	  
	  // create list for eligible users
	  ArrayList<User> eligibleUsers = new ArrayList<User>();
	  
	  // run findToAssign, returns a lit of eligible users
	  eligibleUsers = allUsers.findToAssign(taskCat, allTasks);
	  
	  // get a random index
	  int numEligible = eligibleUsers.size();
	  int index = ThreadLocalRandom.current().nextInt(0, numEligible);
	  
	  // assign to random caretaker
	  caretaker = eligibleUsers.get(index).getUsername();
	  
	  db.insertTaskList(taskID, caretaker, dateIssued, completed, timeTaken, issue, issueDesc, signedOff, signedOffOn, dateDue);
	  
	  
	  System.out.println("Task No. " + taskID + ". " + taskName + ". Category: " + taskCat + ". Allocated to: " + caretaker);
  } // function
  
  /*
   * function to allocate to a specific caretaker
   * 
   * @param username caretaker to allocate to
   */
  public void assignToCaretaker(String username) {
	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  Date currentDate = new Date();
	  // set date to today
	  dateIssued = dateFormat.format(currentDate);
	  
	  // set the caretaker 
	  caretaker = username;
  }
  
  public void deallocateTask() {
	  caretaker = "Not Assigned";
	  dateIssued = null;
  }
  
  public void completeTask(/* int timeTaken */) {
	  completed = true;
	  // this.timeTaken = timeTaken;
  }
  
  public void uncompleteTask() {
	  completed = false;
	  timeTaken = 0;
  }
  
  public void signOffTask() {
	  signedOff = true;
	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  Date currentDate = new Date();
	  signedOffOn = dateFormat.format(currentDate);
  }
  public static class TaskBuilder {
    // required
	private int jobID;
    private int taskID; 
    private String taskName; 
    private String taskDesc;
    private String taskCat;
    private String priority;
    private int repeating;
    private int timeEstimate;
    private String location;
    private String firstAllocation;
    private String lastAllocated;
    
    // optional
    private String caretaker;
    private boolean completed; 
    private int timeTaken;
    private String dateIssued;
    private String dateDue;
    private boolean issue;
    private String issueDesc; 
    private boolean signedOff;
    private String signedOffOn;
    // private String lastCompleted
    
    /*public TaskBuilder(int taskID) {
      this.taskID = taskID;
    }*/
    
    public TaskBuilder jobID (int val) {
    	jobID = val; 
    	return this;
    }
    
    public TaskBuilder taskID (int val) {
      taskID = val;
      return this;
    }
    
    public TaskBuilder taskDesc (String val) {
      taskDesc = val;
      return this;
    }
    

    public TaskBuilder taskCat (String val) {
      taskCat= val;
      return this;
    }
    
    public TaskBuilder priority (String val) {
      priority = val;
      return this;
    }
    
    public TaskBuilder repeating (int val) {
      repeating = val;
      return this;
    }
    
    public TaskBuilder timeEstimate (int val) {
      timeEstimate = val;
      return this;
    }
    
    public TaskBuilder taskName (String val) {
      taskName = val;
      return this;
    }
    public TaskBuilder caretaker (String val) {
      caretaker = val;  
      return this;
    }
    
    public TaskBuilder completed (boolean val) {
      completed = val;
      return this;
    }
     
    public TaskBuilder timeTaken (int val) {
      timeTaken = val;
      return this;
    }
    
    public TaskBuilder dateIssued(Date val) {
  	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  	  if (val != null) {
  		  dateIssued = dateFormat.format(val);
  	  }
  	  else {
  		  dateIssued = null;
  	  }
      return this;
    }
    
    public TaskBuilder dateDue(String val) {
      dateDue = val;
      return this;
    }
    
    public TaskBuilder issue(boolean val) {
    	issue = val;
    	return this;
    }
    
    public TaskBuilder issueDesc(String val) {
      issueDesc = val;
      return this;
    }
    
    public TaskBuilder signedOff(boolean val) {
      signedOff = val;
      return this;
    }
    
    public TaskBuilder location(String val) {
      location = val;
      return this;
    }
    
    public TaskBuilder firstAllocation(Date val) {
	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  	  if(val != null) {
  		  firstAllocation = dateFormat.format(val);
  	  }
  	  else {
  		  firstAllocation = null;
  	  }
	  return this;
    }
    
    public TaskBuilder lastAllocated(Date val) {
  	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  	  if(val != null) {
  		  lastAllocated = dateFormat.format(val);
  	  }
  	  else {
  		  lastAllocated = null;
  	  }
	  return this;
    }
    
    public TaskBuilder signedOffOn(Date val) {
	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  if (val != null) {
		  signedOffOn = dateFormat.format(val);
	  }
	  else {
		  signedOffOn = null;
	  }
      return this;
    }
    public Task build() { 
      if (taskID == 0) {
        throw new IllegalStateException("");
      }
      
      if (taskCat == null) {
        throw new IllegalStateException("");
      }
            
      if (priority == null) {
          throw new IllegalStateException("");
        }
              
              /*
        if (taskName == null) {
          throw new IllegalStateException("");
        }
              /*
        if (taskDesc == null) {
          throw new IllegalStateException("");
        }
              /*
        if (timeEstimate == 0) {
          throw new IllegalStateException("");
        }
              /*
        if (location == null) {
          throw new IllegalStateException("");
        }*/
        
        return new Task(this);
      }
    }
   
  }