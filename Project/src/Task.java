import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
 
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
    if (caretaker != null) {
      return caretaker;
    }
    
    
    return "caretaker unassigned";
  }
  
  public String getDateIssued() {
	  return dateIssued;
  }
  
  /*
   * allocates the task to a caretaker. Sets the dateAllocated to today, 
   * calls the finToAssign function to get a list of eligible caretakers and 
   * randomly selects one of them to assign the task to.
   */
  public void allocateTask() {
	  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:SS");
	  Date currentDate = new Date();
	  dateIssued = dateFormat.format(currentDate);
	  
	  
	  Database database = new Database();
	  
	  TaskList allTasks = new TaskList();
	  UserList allUsers = new UserList();
	  ArrayList<User> eligibleUsers = new ArrayList<User>();
	  allTasks = database.getTasks();
	  allUsers = database.getUsers();
	  
	  eligibleUsers = allUsers.findToAssign(taskCat, allTasks);
	  
	  int numElligible = eligibleUsers.size();
	  int index = ThreadLocalRandom.current().nextInt(0, numElligible);
	  
	  caretaker = eligibleUsers.get(index).getUsername();
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