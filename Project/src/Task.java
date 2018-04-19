import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
 
/**
 * stores information on a task
 * @author Jesse
 *
 */
public class Task {
  private int taskID; 
  private String taskName; 
  private String taskDesc;
  private String taskCat;
  private String priority;
  private int repeating;
  private int timeEstimate;
  private String location;
  
  private String caretaker;
  private boolean completed; 
  private Date completedOn;
  private int timeTaken;
  private String dateIssued;
  private Date dateDue;
  private String issueDesc; 
  private boolean signedOff;
  // private String lastCompleted;
  
  // constructor takes a builder as parameter
  private Task(TaskBuilder builder) {
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
    completedOn = builder.completedOn;
    timeTaken = builder.timeTaken;
    dateIssued = builder.dateIssued;
    dateDue = builder.dateDue;
    issueDesc = builder.issueDesc;
    signedOff = builder.signedOff;
    // lastCompleted = builder.lastCompleted;
  }

  //getters
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
  
  public String getDateIssued() {
	  return dateIssued;
  }
  
  /*
   * allocates the task to a caretaker. Sets the dateAllocated to today, 
   * calls the finToAssign function to get a list of eligible caretakers and 
   * randomly selects one of them to assign the task to.
   */
  public void allocateTask(UserList allUsers, TaskList allTasks) {
	  // new date format
	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	  Date currentDate = new Date();
	  // set date to today
	  dateIssued = dateFormat.format(currentDate);

	  // create list for eligible users
	  ArrayList<User> eligibleUsers = new ArrayList<User>();
	  
	  // run findToAssign, returns a lit of eligible users
	  eligibleUsers = allUsers.findToAssign(taskCat, allTasks);
	  
	  // get a random index
	  int numEligible = eligibleUsers.size();
	  int index = ThreadLocalRandom.current().nextInt(0, numEligible);
	  
	  // assign to random caretaker
	  caretaker = eligibleUsers.get(index).getUsername();
	  
	  
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
  
  public static class TaskBuilder {
    // required
    private int taskID; 
    private String taskName; 
    private String taskDesc;
    private String taskCat;
    private String priority;
    private int repeating;
    private int timeEstimate;
    private String location;
    
    // optional
    private String caretaker;
    private boolean completed; 
    private Date completedOn;
    private int timeTaken;
    private String dateIssued;
    private Date dateDue;
    private String issueDesc; 
    private boolean signedOff;
    // private String lastCompleted
    
    /*public TaskBuilder(int taskID) {
      this.taskID = taskID;
    }*/
    
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
    
    public TaskBuilder completedOn(Date val) {
      completedOn = val;
      return this;
    }
    
    
    public TaskBuilder timeTaken (int val) {
      timeTaken = val;
      return this;
    }
    
    public TaskBuilder dateIssued(String val) {
      dateIssued = val;
      return this;
    }
    
    public TaskBuilder dateDue(Date val) {
      dateDue = val;
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
    
    /*
    public TaskBuilder lastCompleted(String va) {
    	lastCompleted = val;
    	return this;
    }
    */
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