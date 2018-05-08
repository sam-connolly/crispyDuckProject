/*
 * Class to store all ActiveTasks in an ArrayList, with various functions 
 * to use the data
 */
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.swing.table.DefaultTableModel;

public class TaskList {
	private ArrayList<Task> taskList;
	
	public TaskList() 
	{
		taskList = new ArrayList<Task>();
	}
	
	/*
	 * add a task to the list
	 * @param the task to add
	 */
	public void addTask(Task taskToAdd) 
	{
		taskList.add(taskToAdd);
	}
	
	public ArrayList<Task> getTaskList()
	{
		return taskList;
	}
	
	/*
	 * get the number of tasks of a specified category the specified user 
	 * has been assigned. If they haven't been assigned any then return 1.
	 * This is because the calculation to determine an assignment rating will do
	 * preference level / number assigned. Therefore number assigned should'nt be 0.
	 * 
	 * @pram user user to check
	 * @param taskCat the task category to count
	 * @return the number of tasks in the category they have been assigned
	 */
	public int numTasksInCat(String user, String taskCat) {
		// number assigned
		int numAssigned = 1;
		
		// loop through all tasks
		for (Task taskChecking : taskList) {
			// check if user and taskCat match
			if ((taskChecking.getCaretaker().equals(user)) && (taskChecking.getTaskCat().equals(taskCat))) {
				// increment count
				numAssigned = numAssigned + 1;
			} // if 
		} // for
		
		// System.out.println(user + " " + taskCat + " " + numAssigned);
		return numAssigned;
	} // function
	
	/*
	 * deallocates all task assigned for a certain user
	 */
	public void deallocateForUser(String username) {
		for (Task taskToCheck : taskList) {
			// if caretaker matches
			if (taskToCheck.getCaretaker().equals(username)) {
				// deallocate task
				taskToCheck.deallocateTask();
			} // if
		} // for 
	} // function
	
	/**
	 * checks if a task needs to be allocated and calls allocate task if it is
	 * 
	 * @param taskToCheck task to check if allocation is due
	 * @param allUsers list of all users
	 * @param allTasks list of all tasks
	 * 
	 */
	@SuppressWarnings("unused")
	public String checkIfDueAllocation(Task taskToCheck) throws ParseException, SQLException {
		  // new date format
		  // convert task date information from string to Dates
		  DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		  Date currentDate = new Date();
		  Date taskLastAllocated = convertStringToDate(taskToCheck.getLastAllocated());
		  Date dateFirstAllocation = convertStringToDate(taskToCheck.getFirstAllocation());
		  
		  // get how often the task repeats
		  int repeating = taskToCheck.getRepeating();
		  
		  // if the task has been allocated before
		  if (taskLastAllocated != null) {
			  // check if the task repeats and 
			  // check if the time since it was last allocated is greater than or equal to how often it repeats
			  long numDaysSinceLastAllocation = TimeUnit.DAYS.convert((currentDate.getTime() - taskLastAllocated.getTime()), TimeUnit.MILLISECONDS);
			  System.out.println(numDaysSinceLastAllocation);
			  if(((repeating != 0) && (numDaysSinceLastAllocation >= repeating))) {
				  // if true allocate task
				  return "DUE ALLOCATION";
			  } // if
		  } // if
		  else {
			  // if the current date is on or after the date specified to begin allocating this task and 
			  // it hasnt been allocated
			  if((currentDate.equals(dateFirstAllocation) || currentDate.after(dateFirstAllocation))
					  && (taskToCheck.getCaretaker() == "Not Assigned")) {
				  // allocate the task
				  return "DUE ALLOCATION";
			  } // if
		  } // else 
		  return "NOT DUE";
	} // function
	
	/**
	 * checks if any of the tasks in the list were signed off 6 or more months ago and removes
	 * the job from the list of tasks and from the TaskList table
	 * 
	 * @throws ParseException
	 * @throws SQLException
	 */
	public void checkSignedOffDate() throws ParseException, SQLException { 
		// calendar dates for month calculation
		Calendar startCalendar;
		Calendar endCalendar;
		// for all tasks
		for (Task taskToCheck : taskList) {
			// if the task has been signed off
			if (taskToCheck.getSignedOff()) {
				// get current day and date it was signed off
				startCalendar = new GregorianCalendar();
				startCalendar.setTime(convertStringToDate(taskToCheck.getSignedOffOn()));
				endCalendar = new GregorianCalendar();
				endCalendar.setTime(new Date());
				
				// calculate the difference in months
				int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
				int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
				
				System.out.println(diffMonth);
				
				// if the difference in months is greater than 6 
				if(diffMonth > 6) {
					// remove it from the database and the task list
					Database db = new Database();
					db.removeFromTaskList(taskToCheck.getJobID());

					taskList.remove(taskToCheck);
				}
			}
		}
	}
	
	/* 
	 * converts a date from a string to a usable Date
	 * 
	 * @param dateString date as string to convert
	 */
	public Date convertStringToDate(String dateString) throws ParseException {
		Date convertedDate;
		  // if the string is not null
		  if(dateString != null) {
			  // convert string
			  convertedDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);  
		  }
		  else {
			  // else converted date is null
			  convertedDate = null;
		  }
		  // return converted date
		  return convertedDate;
	}
	
	/** 
	 * set when it was last allocated
	 * @param taskID the ID of the task to update
	 * @param lastAllocation when it was lasta allocated
	 */
	public void updateLastAllocation(int taskID, String lastAllocation) { 
		for (Task taskToUpdate : taskList) {
			if(taskToUpdate.getTaskID() == taskID) {
				taskToUpdate.setLastAllocated(lastAllocation);
			}
		}
	}
	
	/**
	 * get a table model containing all tasks allocated to a caretaker
	 * @param username caretaker you want tasks for
	 * @return table model with all tasks allocated to caretaker
	 */
	public DefaultTableModel getAllocatedToCaretaker(String username) {
		DefaultTableModel allocatedToCaretakerModel = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		Object[] row = new Object[5];
		allocatedToCaretakerModel.addColumn("Job ID");
		allocatedToCaretakerModel.addColumn("Task Name");
		allocatedToCaretakerModel.addColumn("Location");
		allocatedToCaretakerModel.addColumn("Priority");
		allocatedToCaretakerModel.addColumn("Date Due");
		for (Task taskToCheck : taskList ) {
			if (taskToCheck.getCaretaker().equals(username) && taskToCheck.getCompleted() == false) { 
				row[0] = taskToCheck.getJobID();
				row[1] = taskToCheck.getTaskName();
				row[2] = taskToCheck.getLocation();
				row[3] = taskToCheck.getPriority();
				row[4] = taskToCheck.getDateDue();
				
				allocatedToCaretakerModel.addRow(row);
			}
		}
		
		return allocatedToCaretakerModel;
	}
	
	/**
	 * get a table model containing all tasks completed by a caretaker
	 * @param username caretaker you want tasks completed by
	 * @return table model with all tasks completed by the caretaker
	 */
	public DefaultTableModel getCompletedByCaretaker(String username) {
		DefaultTableModel completedByCaretakerModel = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		Object[] row = new Object[6];
		completedByCaretakerModel.addColumn("Job ID");
		completedByCaretakerModel.addColumn("Task Name");
		completedByCaretakerModel.addColumn("Location");
		completedByCaretakerModel.addColumn("TimeTaken");
		completedByCaretakerModel.addColumn("Signed Off");
		completedByCaretakerModel.addColumn("Signed Off On");
		for (Task taskToCheck : taskList ) {
			if (taskToCheck.getCaretaker().equals(username) && taskToCheck.getCompleted() == true) { 
				row[0] = taskToCheck.getJobID();
				row[1] = taskToCheck.getTaskName();
				row[2] = taskToCheck.getLocation();
				row[3] = taskToCheck.getFormattedTimeTaken();
				row[4] = taskToCheck.getSignedOff();
				row[5] = taskToCheck.getSignedOffOn();
				
				completedByCaretakerModel.addRow(row);
			}
		}
		
		return completedByCaretakerModel;
	}
	
	/**
	 * get a table model of unallocated tasks, either all unallocated,
	 * ones which are due allocation or only ones which aren't, based on 
	 * a filter you pass as an argument
	 * @param filter what model to get
	 * @return a table model with unallocated tasks
	 * @throws ParseException
	 * @throws SQLException
	 */
	public DefaultTableModel getAllUnallocated(String filter) throws ParseException, SQLException {
		DefaultTableModel allUnallocatedModel = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		Object[] row = new Object[6];
		ArrayList<Integer> displayedTasks = new ArrayList<Integer>();
		boolean displayTask = true;
		
		allUnallocatedModel.addColumn("Task ID");
		allUnallocatedModel.addColumn("Task Name");
		allUnallocatedModel.addColumn("Due Allocation");
		allUnallocatedModel.addColumn("Priority");
		allUnallocatedModel.addColumn("Last Allocated");
		allUnallocatedModel.addColumn("Next Allocation");
		if( filter == "All Unallocated") {
			for (Task taskToCheck : taskList) {
				for(int taskID : displayedTasks) {
					System.out.println(taskID);
					if (taskID == taskToCheck.getTaskID()) {
						System.out.println("Who's got a match!");
						displayTask = false;
					}
				}
				if (taskToCheck.getRepeating() == 0 && taskToCheck.getLastAllocated() != null) {
					displayTask = false;
				}
				
				if (displayTask == true) {
					displayedTasks.add(taskToCheck.getTaskID());
					
					row[0] = taskToCheck.getTaskID();
					row[1] = taskToCheck.getTaskName();
					row[2] = checkIfDueAllocation(taskToCheck);
					row[3] = taskToCheck.getPriority();
					row[4] = taskToCheck.getLastAllocated();
					row[5] = taskToCheck.getNextAllocation();
					
					allUnallocatedModel.addRow(row);
				}
				displayTask = true;
			}
		}
		
		if( filter == "Due Allocation") {
			for (Task taskToCheck : taskList) {
				for(int taskID : displayedTasks) {
					System.out.println(taskID);
					if (taskID == taskToCheck.getTaskID()) {
						System.out.println("Who's got a match!");
						displayTask = false;
					}
				}
				if (!(checkIfDueAllocation(taskToCheck) == "DUE ALLOCATION" && taskToCheck.getCaretaker() == "Not Assigned")) {
					displayTask = false;
				}
				
				if (displayTask == true) {
					displayedTasks.add(taskToCheck.getTaskID());
					
					row[0] = taskToCheck.getTaskID();
					row[1] = taskToCheck.getTaskName();
					row[2] = "DUE ALLOCATION";
					row[3] = taskToCheck.getPriority();
					row[4] = taskToCheck.getLastAllocated();
					row[5] = taskToCheck.getNextAllocation();
					
					allUnallocatedModel.addRow(row);
				}
				displayTask = true;
			}
		}
		
		if( filter == "Not Due Allocation") {
			for (Task taskToCheck : taskList) {
				for(int taskID : displayedTasks) {
					System.out.println(taskID);
					if (taskID == taskToCheck.getTaskID()) {
						System.out.println("Who's got a match!");
						displayTask = false;
					}
				}
				if ((checkIfDueAllocation(taskToCheck) != "NOT DUE") || 
						(taskToCheck.getRepeating() == 0 && taskToCheck.getLastAllocated() != null)) {
					displayTask = false;
				}
				
				if (displayTask) {
					displayedTasks.add(taskToCheck.getTaskID());
					
					row[0] = taskToCheck.getTaskID();
					row[1] = taskToCheck.getTaskName();
					row[2] = "NOT DUE";
					row[3] = taskToCheck.getPriority();
					row[4] = taskToCheck.getLastAllocated();
					row[5] = taskToCheck.getNextAllocation();
					
					allUnallocatedModel.addRow(row);
				}
				displayTask = true;
			}
		}
		return allUnallocatedModel;
	}

	/**
	 * get a model of all allocated tasks regardless of who they are
	 * allocated to
	 * @return returns a table model with all allocated tasks
	 * @throws ParseException
	 * @throws SQLException
	 */
	public DefaultTableModel getAllAllocated() throws ParseException, SQLException {
		DefaultTableModel allAllocatedModel = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		Object[] row = new Object[6];
		allAllocatedModel.addColumn("Job ID");
		allAllocatedModel.addColumn("Task Name");
		allAllocatedModel.addColumn("Allocated To");
		allAllocatedModel.addColumn("Issued On");
		allAllocatedModel.addColumn("Due On");
		allAllocatedModel.addColumn("Priority");
		
		for (Task taskToCheck : taskList) {
			if ( taskToCheck.getCaretaker() != "Not Assigned" && taskToCheck.getCompleted() == false) {
				row[0] = taskToCheck.getJobID();
				row[1] = taskToCheck.getTaskName();
				row[2] = taskToCheck.getCaretaker();
				row[3] = taskToCheck.getDateIssued();
				row[4] = taskToCheck.getDateDue();
				row[5] = taskToCheck.getPriority();
				
				allAllocatedModel.addRow(row);
			}
		}
		return allAllocatedModel;
	}
	
	/** 
	 * get a table model containing all completed tasks regardless
	 * of who they were completed by 
	 * @return table model containing all completed tasks
	 * @throws ParseException
	 * @throws SQLException
	 */
	public DefaultTableModel getAllCompleted() throws ParseException, SQLException {
		DefaultTableModel allCompletedModel = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		Object[] row = new Object[5];
		allCompletedModel.addColumn("Job ID");
		allCompletedModel.addColumn("Task Name");
		allCompletedModel.addColumn("Allocated To");
		allCompletedModel.addColumn("Signed Off");
		allCompletedModel.addColumn("Signed Off On");
		
		for (Task taskToCheck : taskList) {
			if (taskToCheck.getCompleted()) {
				row[0] = taskToCheck.getJobID();
				row[1] = taskToCheck.getTaskName();
				row[2] = taskToCheck.getCaretaker();
				if (taskToCheck.getSignedOff()){
					row[3] = "Yes";
				}
				else {
					row[3] = "No";
				}
				row[4] = taskToCheck.getSignedOffOn();
				
				allCompletedModel.addRow(row);
			}
		}
		return allCompletedModel;
	}
	

	/**
	 * get the size of the task list
	 * @return size of the task list
	 */
	public int getSize() {
		return taskList.size();
	}
	
	public Task getTaskIndexed(int index) {
		return taskList.get(index);
	}
	
	/*
	 * return first task matching a certain taskID
	 */
	public Task getTaskWithJobID(int jobID) {
		// loop through all tasks
		for (Task taskToCheck : taskList) {
			// if task is not assigned
			if(taskToCheck.getJobID() == jobID) {
				return taskToCheck;
			}
		} // for 
		
		return null;
	}
	
	/*
	 * return first task matching a certain taskID
	 */
	public Task getFirstTaskWithTaskID(int taskID) {
		// loop through all tasks
		for (Task taskToCheck : taskList) {
			// if task is not assigned
			if(taskToCheck.getTaskID() == taskID) {
				return taskToCheck;
			}
		} // for 
		
		return null;
	}
	
	/*
	 * return alll tasks matching a certain taskID
	 */
	public ArrayList<Task> getTasksWithTaskID(int taskID) {
		ArrayList<Task> tasksMatchingID = null;
		// loop through all tasks
		for (Task taskToCheck : taskList) {
			// if task is not assigned
			if(taskToCheck.getTaskID() == taskID) {
				tasksMatchingID.add(taskToCheck);
			}
		} // for 
		
		return tasksMatchingID;
	}

}


