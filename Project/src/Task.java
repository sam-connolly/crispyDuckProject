import java.util.ArrayList;
import java.util.Calendar;
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
  private boolean caretakerSignOff;
  
  private String caretaker;
  private boolean completed; 
  private int timeTaken;
  private String dateIssued;
  private String dateDue;
  private boolean issue;
  private String issueDesc; 
  private boolean signedOff;
  private String signedOffOn;
  
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
    caretakerSignOff = builder.caretakerSignOff;
    timeGiven = builder.timeGiven;
  }

  // getters / setters

  /**
  * Getter for the jobID
  *
  * @return	The jobID
  */
  public int getJobID() {
	  return jobID;
  }
  
  /**
  * Getter for the taskID
  *
  * @return	The taskID
  */
  public int getTaskID() {
    return taskID;
  }
  
  /**
  * Getter for the task name
  *
  * @return	The name of the task
  */
  public String getTaskName() {
    return taskName;
  }
  
  /**
  * Getter for the task description
  *
  * @return	The description of the task
  */
  public String getTaskDesc() {
    return taskDesc;
  }
  
  /**
  * Getter for the category of the task
  *
  * @return	The category of the task
  */
  public String getTaskCat() {
    return taskCat;
  }
  
  /**
  * Getter for the priority of the task
  *
  * @return	The priority of the task
  */
  public String getPriority() {
    return priority;
  }
  
  /**
  * Getter for the location of the task
  *
  * @return	The location of the task
  */
  public String getLocation() {
    return location;
  }
  
  /**
  * Getter for the number of days to pass before the task repeats
  *
  * @return	The number of days to pass before the task repeats
  */
  public int getRepeating() {
    return repeating;
  }
  
  /**
  * Getter for the estimated time ot complete the task
  *
  * @return	The estimated time to complete the task, in minutes
  */
  public Integer getTimeEstimateInt()
  {
	  return timeEstimate;
  }
  
  /**
   * get time estimate in string form
   * @return time estimate formatted
   */
  public String getTimeEstimateString() { 
	   return timeEstimate/24/60 + " days, " + timeEstimate/60%24 + " hours, " + timeEstimate%60 + " minutes.";
	 }
  
  /*
   *  getter for caretaker returns either the username of the caretaker if
   *  it's been allocated, or "Not Assigned" if it hasn't. getCaretaker() cannot return null
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
  
  /**
  * Getter for the number of days for a task to be completed in
  *
  * @return	The number of days a task must be completed in
  */
  public int getTimeGiven() { 
	  return timeGiven;
  }
  
  /**
  * Getter for if the task has been signed off
  *
  * @return	Whether the task has been signed off
  */
  public boolean getSignedOff() {
	  return signedOff;
  }
  
  /**
   * formatted sign off
   * @return returns signed off as Yes or No
   */
  public String getSignedOffFormatted() {
	  if (signedOff == true) {
		  return "Yes";
	  }
	  else {
		  return "No";
	  }
  }
  
  /**
  * Getter for the date the job was signed off on
  *
  * @return	The date the job was signed off on
  */
  public String getSignedOffOn() { 
	  return signedOffOn;
  }
  
  /**
  * Getter for the date the job was issued
  *
  * @return	The date the job was issued
  */
  public String getDateIssued() {
	  return dateIssued;
  }
  
  /**
  * Getter for the due date of the job
  *
  * @return	The due date of the job
  */
  public String getDateDue()
  {
	  return dateDue;
  }
  
  /**
  * Getter for the date of last allocation
  *
  * @return	The date of last allocation
  */
  public String getLastAllocated() {
	  return lastAllocated;
  }
  
  /**
   * get the next date that this task should be allocated
   * @return next date for allocation
   * @throws ParseException
   */
  public String getNextAllocation() throws ParseException {
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  Calendar c = Calendar.getInstance();
	  // if the task has been allocated
	  if (lastAllocated != null) {
		  // add the repeating value on to the last allocation date
		  Date lastAllocatedDate = sdf.parse(lastAllocated);
		  c.setTime(lastAllocatedDate); 
		  c.add(Calendar.DATE, repeating);
		  // return the next allocation date
	  	  return sdf.format(c.getTime());
	  }
	  else { 
		  // else the next allocation date is the first allocation date
	  	  return firstAllocation;
	  }
  }
  
  /**
  * Getter for the date of first allocation
  *
  * @return	The date of first allocation
  */
  public String getFirstAllocation() {
	  return firstAllocation;
  }
  
  /**
  * Getter for if the job has been completed
  *
  * @return	Whether the job has been completed
  */
  public Boolean getCompleted()
  {
	  return completed;
  }
  
  /**
  * Getter for if there's an issue with the job
  *
  * @return	TWhether there's an issue with the job
  */
  public Boolean getIssue()
  {
	  return issue;
  }
  
  /**
  * Getter for the description of the issue
  *
  * @return	The description of the issue
  */
  public String getIssueDesc()
  {
	  return issueDesc;
  }
  
  /**
  * Getter for if the caretaker can sign off on the job
  *
  * @return	Whether a caretaker can sign off the job
  */
  public boolean getCaretakerSignOff()
  {
	  return caretakerSignOff;
  }
  
  /**
  * Getter for the time taken to complete the job
  *
  * @return	The time taken ot complete the job
  */
  public int getTimeTaken() {
	  return timeTaken;
  }
  
  /**
  * Getter for the formatted string version of the time taken
  *
  * @return	The formatted string version of the time taken
  */
  public String getFormattedTimeTaken() { 
	  int hours = timeTaken / 60;
	  int minutes = timeTaken - (hours * 60);
	  return (hours + " hours " + minutes + " minutes.");
  }
  
  public void setLastAllocated(String lastAllocated) {
	  this.lastAllocated = lastAllocated;
  }
  
  
  /*
   * function to allocate to a specific caretaker
   * 
   * @param username caretaker to allocate to
   */
  public void assignToCaretaker(String username, TaskList allTasks) throws SQLException, ParseException {
	  Database db = new Database();
	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  Date currentDate = new Date();
	  // set date to today
	  // set dateIssued and last allocated to today
	  dateIssued = dateFormat.format(currentDate);
	  lastAllocated = dateFormat.format(currentDate);

	  // assign a dueDate
	  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  Calendar c = Calendar.getInstance();
	  c.setTime(new Date()); 
	  c.add(Calendar.DATE, timeGiven);
  	  dateDue = sdf.format(c.getTime());
  	  System.out.print(dateDue);


	  db.updateLastAllocated(taskID, lastAllocated);
	  allTasks.updateLastAllocation(taskID, lastAllocated);
	  
	  // set the caretaker 
	  caretaker = username;
	  
	  db.insertTaskList(taskID, caretaker, dateIssued, completed, timeTaken, issue, issueDesc, signedOff, signedOffOn, dateDue);
  }
  
  public void setHighestPriority()
  {
	  priority = "1";
  }
  
  /**
   * deallocate a task
   */
  public void deallocateTask() {
	  caretaker = "Not Assigned";
	  dateIssued = null;
  }
  
  /**
   * completes a task
   * @param timeTaken the amount of time taken to complete the task
   * @throws SQLException
   * @throws ParseException
   */
  public void completeTask(int timeTaken) throws SQLException, ParseException {
	  Database database = new Database();
	  // set completed to true
	  completed = true;
	  
	  // update database
	  database.completeTask(jobID, timeTaken);
	  // if the caretaker can sign off this task
	  if (caretakerSignOff == true) { 
		  // sign it off
		  signOffTask();
	  }
	  this.timeTaken = timeTaken;
	  
	  // calculate how efficient they were
	  float efficiencyForTask = (float) timeEstimate / (float) timeTaken;
	  UserList allUsers = database.getUsers();
	  
	  // update the users average efficiency for this category of task
	  allUsers.getUserCaretaker(caretaker).updateEfficiency(taskCat, efficiencyForTask);
  }
  
  /**
   * uncompletes a task
   * @throws SQLException
   */
  public void uncompleteTask() throws SQLException {
	  Database database = new Database();
	  UserList allUsers = database.getUsers();
	  completed = false;
	  
	  // revert update to efficiency
	  float efficiencyForTask = (float) timeEstimate / (float) timeTaken;
	  allUsers.getUserCaretaker(caretaker).undoUpdateEfficiency(taskCat, efficiencyForTask);
	  timeTaken = 0;
	  signedOff = false;
	  signedOffOn = null;
	  
	  database.uncompleteTask(jobID);
  }
  
  /**
   * sign off a task
   * @throws SQLException
   * @throws ParseException
   */
  public void signOffTask() throws SQLException, ParseException {
	  // set sign off date to the current date
	  Database database = new Database();
	  signedOff = true;
	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  Date currentDate = new Date();
	  signedOffOn = dateFormat.format(currentDate);
	  
	  // update database
	  database.signOffTask(jobID, signedOffOn);
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
    private boolean caretakerSignOff;
    private int timeGiven;
    
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
    
    
    // builders
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
    
    public TaskBuilder dateDue(Date val) {
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	  if (val != null) {
    		  dateDue = dateFormat.format(val);
    	  }
    	  else {
    		  dateDue = null;
    	  }
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
    
    public TaskBuilder caretakerSignOff(boolean val) { 
    	caretakerSignOff = val;
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
    
    public TaskBuilder timeGiven(int val) {
    	timeGiven = val;
    	return this;
    }
    
    public Task build() { 
      if (taskID == 0) {
        throw new IllegalStateException("No ID");
      }
      
      if (taskCat == null) {
        throw new IllegalStateException("No Category");
      }
            
      if (priority == null) {
          throw new IllegalStateException("No Priority");
        }
              
        if (taskName == null) {
          throw new IllegalStateException("No Name");
        }
     
        return new Task(this);
      }
    }
  }
