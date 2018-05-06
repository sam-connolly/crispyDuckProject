import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Database {
	private String driver = "jdbc:ucanaccess://";
	private String Db = "database//crispyDuckDatabase.accdb";
	private Connection conn = null;
	private String url = driver + Db;
	private String user, password, forename, surname;
	private boolean admin;
	
	public Database() {
		connect();
	}
	
	public boolean connect() {
		try {
			conn = DriverManager.getConnection(url);
			System.err.println("yay");
			return true;
		} catch (SQLException e) {
			/* JOptionPane.showMessageDialog(new JFrame(),
				    "Could not establish databse connection. Contact database administrator",
				    "Database error",
				    JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(); */
			return false;
		}
	}	
	
	public String getPassword(String username) {
		String password=null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT PasswordHash FROM User WHERE Username = '"+ username +"'");
			ResultSet rs = stmt.executeQuery();
			boolean moreRecords = rs.next();
			//If there are no records to show return null
		    if (!moreRecords) {
			      System.out.println ("ResultSet contained no records");
			      return null;
		    }
			password = rs.getString("PasswordHash");
		}
		catch(Exception e) {	
			// TODO: handle exception
		}
		return password;
	}
	
	public String getForename(String username) {
		String fName=null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT fName FROM User WHERE Username = '"+ username +"'");
			ResultSet rs = stmt.executeQuery();
			boolean moreRecords = rs.next();
			//If there are no records to show return null
		    if (!moreRecords) {
			      System.out.println ("ResultSet contained no records");
			      return null;
		    }
		    fName = rs.getString("fName");
		}
		catch(Exception e) {	
			// TODO: handle exception
		}
		System.out.println(fName);
		return fName;
	}
	
	public String getSurname(String username) {
		String sName=null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT sName FROM User WHERE Username = '"+ username +"'");
			ResultSet rs = stmt.executeQuery();
			boolean moreRecords = rs.next();
			//If there are no records to show return null
		    if (!moreRecords) {
			      System.out.println ("ResultSet contained no records");
			      return null;
		    }
		    sName = rs.getString("sName");
		}
		catch(Exception e) {	
			// TODO: handle exception
		}
		return sName;
	}
	
	public boolean validateLogin(String user,  String password) {
		boolean validLogin = false;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT PasswordHash FROM User WHERE Username = '"+ user +"'");
			ResultSet rs = stmt.executeQuery();
			boolean moreRecords = rs.next();
			//If there are no records to show validLogin is set to false
		    if (!moreRecords) {
			      System.out.println ("ResultSet contained no records");
			      return false;
		    }
			//If the entered password matches the one stored in the database
			//validLogin is set to true
			if ((password.equals(rs.getString("PasswordHash")))) {
				validLogin=true;
				System.out.println("Sucess");
			}
		}
		catch(Exception e) {	
			// TODO: handle exception
		}
		//Return validLogin to check if login was successful 
		System.out.println(validLogin);
		return validLogin;
	}
	
	public boolean checkRole(String user) {
		boolean isAdmin = false;
		try {
			PreparedStatement stmt = conn.prepareStatement("Select Username, Admin FROM User WHERE Username = '"+ user +"'");
			ResultSet rs = stmt.executeQuery();
			boolean moreRecords = rs.next();
			if(!moreRecords) {
				System.out.println("Result set contained no records");
				return false;
			}
			System.out.println(rs.getString("Username") + " is admin? " + rs.getBoolean("Admin"));
			isAdmin = rs.getBoolean("Admin");
		}
		catch(Exception e) {	
			// TODO: handle exception
		}
		System.out.println("Admin Check: " + isAdmin);
		return isAdmin;
	}
	
	public boolean addUser(String username, String password, Boolean admin, 
			String forename, String surname) throws SQLException{
		PreparedStatement sqlInsert = null;
		try {
			sqlInsert = conn.prepareStatement("INSERT INTO User (Username, "
					+ "PasswordHash, Admin, fName, sName) VALUES (?,?,?,?,?)");
		}
		catch (SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
		sqlInsert.setString(1, username);
		sqlInsert.setString(2, password);
		sqlInsert.setBoolean(3, admin);
		sqlInsert.setString(4, forename);
		sqlInsert.setString(5, surname);
		int result = sqlInsert.executeUpdate();
		System.err.println("Result code from insert: " + result);
		if (result == 0) {
			return false;
		}
		else {
			System.err.println("Inserted: " + username + forename + surname);
			return true;
		}
	}
	
	public boolean addUserPreferences(String username) throws SQLException{
		boolean success = true;
		String catName = null;
		PreparedStatement sqlInsert = null;
		for (int i = 1;  i <= 7;  i ++) {
			try {
				sqlInsert = conn.prepareStatement("INSERT INTO CaretakerCategory (CatName, Caretaker,  PreferenceLevel) VALUES (?,?,?) ");
			}
			catch (SQLException sqlex) {
				System.err.println("SQL Exception");
				sqlex.printStackTrace();
			}
			if (i==1) {catName="Interior Cleaning";}
			else if (i==2) {catName="Exterior Repair";}
			else if (i==3) {catName="Exterior Cleaning";}
			else if (i==4) {catName="Interior Repair";}
			else if (i==5) {catName="Light Labour";}
			else if (i==6) {catName="Restocking";}
			sqlInsert.setString(1, catName);
			sqlInsert.setString(2, username);
			sqlInsert.setInt(3, 5);
			int result = sqlInsert.executeUpdate();
			System.err.println("Result code from insert: " + result);
			if (result == 0) {
				return false;
			}
		}
		return success;
	}
	
	
	public boolean deleteUserPreferences(String username) throws SQLException{
		PreparedStatement sqlDelete = null;
		try {
			sqlDelete = conn.prepareStatement("DELETE FROM CaretakerCategory WHERE Caretaker = ?");
		}
		catch (SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
		sqlDelete.setString(1, username);
		int result = sqlDelete.executeUpdate();
		System.err.println("Result code from Delete: " + result);
		if (result == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean deleteUser(String username) throws SQLException{
		boolean deletePreference = deleteUserPreferences(username);
		if (!deletePreference) {
			return false;
		}
		PreparedStatement sqlDelete = null;
		try {
			sqlDelete = conn.prepareStatement("DELETE FROM User WHERE Username = ?");
		}
		catch (SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
		sqlDelete.setString(1, username);
		int result = sqlDelete.executeUpdate();
		System.err.println("Result code from Delete: " + result);
		if (result == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean updateUser(String username, String fName, String sName) throws SQLException{
		PreparedStatement sqlUpdate = null;
		try {
			sqlUpdate = conn.prepareStatement("UPDATE User SET fName = ?, sName = ? WHERE Username = ?");
		}
		catch(SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
		sqlUpdate.setString(1, fName);
		sqlUpdate.setString(2, sName);
		sqlUpdate.setString(3, username);
		int result = sqlUpdate.executeUpdate();
		System.err.println("Result code from Update: " + result);
		if (result == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean updateUserPreference(String username, String cat, int preference) throws SQLException{ 
		PreparedStatement sqlUpdate = null;
		try {
			sqlUpdate = conn.prepareStatement("UPDATE CaretakerCategory SET PreferenceLevel = ? WHERE Caretaker = ? AND CatName = ?");
		}
		catch(SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
		sqlUpdate.setInt(1, preference);
		sqlUpdate.setString(2, username);
		sqlUpdate.setString(3, cat);
		int result = sqlUpdate.executeUpdate();
		System.err.println("Result code from Update: " + result);
		if (result == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean updateUserAdmin(String username, String password, boolean admin, String fName, String sName) throws SQLException{
		PreparedStatement sqlUpdate = null;
		try {
			sqlUpdate = conn.prepareStatement("UPDATE User SET PasswordHash = ?, Admin = ?, fName = ?, sName = ? WHERE Username = ?");
		}
		catch(SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
		sqlUpdate.setString(1, password);
		sqlUpdate.setBoolean(2, admin);
		sqlUpdate.setString(3, fName);
		sqlUpdate.setString(4, sName);
		sqlUpdate.setString(5, username);
		int result = sqlUpdate.executeUpdate();
		System.err.println("Result code from Update: " + result);
		if (result == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean updatePassword(String username, String password) throws SQLException{
		PreparedStatement sqlUpdate = null;
		try {
			sqlUpdate = conn.prepareStatement("UPDATE User SET PasswordHash = ? WHERE Username = ?");
		}
		catch(SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
		sqlUpdate.setString(1, password);
		sqlUpdate.setString(2, username);
		int result = sqlUpdate.executeUpdate();
		System.err.println("Result code from Update: " + result);
		if (result == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getPreferenceLevel(String username, String cat) throws SQLException{
		int preferenceLevel=0;
		System.out.println(username);
		System.out.println(cat);
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT PreferenceLevel FROM CaretakerCategory WHERE Caretaker = '"+ username +"' AND CatName = '"+ cat +"'");
			ResultSet rs = stmt.executeQuery();
			boolean moreRecords = rs.next();
			if (!moreRecords) {
				System.out.println("ResultSet contained no records");
				return 0;
			}
			preferenceLevel=Integer.parseInt(rs.getString("PreferenceLevel"));
		}
		catch(Exception e) {
			
		}
		System.out.println(preferenceLevel);
		return preferenceLevel;
	}

	public float getEfficiency(String username, String cat) throws SQLException{
		float efficiency=0;
		System.out.println(username);
		System.out.println(cat);
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT Efficiency FROM CaretakerCategory WHERE Caretaker = '"+ username +"' AND CatName = '"+ cat +"'");
			ResultSet rs = stmt.executeQuery();
			boolean moreRecords = rs.next();
			if (!moreRecords) {
				System.out.println("ResultSet contained no records");
				return 0;
			}
			efficiency=Float.parseFloat(rs.getString("Efficiency"));
		}
		catch(Exception e) {
			
		}
		System.out.println(efficiency);
		return efficiency;
	}
	
	public ArrayList<String> getCategories()
	{
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT CatName FROM Category";
			ResultSet rs = stmt.executeQuery(query);
			
			ArrayList<String> categories = new ArrayList<String>();
			while (rs.next())
			{
				categories.add(rs.getString("CatName"));
			}
			
			return categories;
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public ArrayList<String> getUsernames()
	{
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT Username FROM User";
			ResultSet rs = stmt.executeQuery(query);
			
			ArrayList<String> users = new ArrayList<String>();
			while (rs.next())
			{
				users.add(rs.getString("Username"));
			}
			
			return users;
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public ArrayList<String> getCaretakers()
	{
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT Username, fName, sName, "
					+ "FROM User "
					+ "WHERE admin = 'false'";
			ResultSet rs = stmt.executeQuery(query);
			
			ArrayList<String> caretakers = new ArrayList<String>();
			while (rs.next())
			{
				String fullName = rs.getString("fName") + " " + rs.getString("sName");
				caretakers.add(rs.getString("Username"));
				caretakers.add(fullName);
			}
			
			return caretakers;
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			return null;
		}
	}

	/*
	 *Function which returns a list of User objects (all users in the database)
	 * 
	 * @return list of all users
	 */
	public UserList getUsers() {
		try {
			Statement stmt = conn.createStatement();
			// query for all users
			String query = "SELECT Username,  PasswordHash, Admin, fName, sName "
						 + "FROM User";
		    ResultSet rs = stmt.executeQuery (query);
		    
		    // create UserList object to store User objects
		    UserList allUsers = new UserList();
		    
		    // loop for all records retrieved
		    while (rs.next ()) {
		    	// assign user information to variables
		    	String username = rs.getString("Username");
		    	String passwordHash = rs.getString("PasswordHash");
		    	boolean admin = rs.getBoolean("Admin");
		    	String fName = rs.getString("fName");
		    	String sName = rs.getString("sName");
		    	
		    	// create ArrayList of TaskCategory objects (contains preference/efficiency info)
			    ArrayList<TaskCategory> preferences = getPreferences(username);
			    
			    // create User object with information on the current user being retrieved
			    User userToAdd = new User(username, passwordHash, admin, fName, sName);
			    // add add the list preferences to the new User object
			    userToAdd.setPreferences(preferences);
			    
			    //add User object to list of all users
			    allUsers.addUser(userToAdd);	
		    } // while
		    //return list of all users
		    return allUsers;
		} // try
		catch (Exception e) {
			// TODO: handle exception
			return null;
		} // catch
	} // function
	
	/*
	 * Queries the database for all of the preference/efficiency information in the
	 * CaretakerCategory table for a specific User, creates a TaskCategory object for each 
	 * category with that information and adds them to a list to be associated with that user (inside their User object)
	 * 
	 * @param user username to retrieve preference information for
	 * @return list of preferences for all categories for that user
	 */
	public ArrayList<TaskCategory> getPreferences(String user) {
		try {
		    ArrayList<TaskCategory> preferences = new ArrayList<TaskCategory>();
	    	
		    // query database for all preference information for "user"
			Statement stmt = conn.createStatement();
			String query = "SELECT CareCat, CatName, Efficiency, PreferenceLevel, NumberCompleted "
						 + "FROM CaretakerCategory"
						 + " WHERE Caretaker = '"+ user +"'";
		    ResultSet rs = stmt.executeQuery (query);
		    // loop for all categories
		    while (rs.next()) {
		    	// assign data to variables
		    	int careCat = rs.getInt("CareCat");
		    	String catName = rs.getString("CatName");
		    	int efficiency = rs.getInt("Efficiency");
		    	int preferenceLevel = rs.getInt("PreferenceLevel");
		    	int numberCompleted = rs.getInt("NumberCompleted");
		    	
		    	// create a TaskCategory object with the current preferences and efficiency for the current category
		    	TaskCategory catToAdd = new TaskCategory(catName, efficiency, preferenceLevel, numberCompleted);
		    	// add the object to a list
		    	preferences.add(catToAdd);
		    } // while
		    // return the list of TaskCategory objects
    		return preferences;	
		} // try
		catch (Exception e) {
			// TODO: handle exception
			return null;
		} // catch
	} // function
	
	/*
	 *  function to retrieve data from TaskList and add it to a collection
	 *  of ActiveTask objects
	 */
	public TaskList getTasks()
	{
		try 
		{
			// query database for data in TaskList
			Statement stmt = conn.createStatement();
			String query = "SELECT JobID, TaskID, Caretaker, DateIssued, DateDue,"
					+ " Completed, TimeTaken, Issue, IssueDesc, SignedOff, signedOffOn, "
					+ " TaskName, TaskDesc, TaskCat, Priority, Repeating, TimeEstimate, Location, "
					+ " FirstAllocation, LastAllocated, TimeGiven"
					+ " FROM Task"
					+ " LEFT JOIN TaskList ON Task.taskID = TaskList.taskID";
			ResultSet rs = stmt.executeQuery(query);
			
			// create a new ActiveTaskList object to store data from results set
			TaskList allActiveTasks = new TaskList();
			
			// iterate over data
			while (rs.next())
			{
				// assign data to variables
				int jobID = rs.getInt("jobID");
				int taskID = rs.getInt("TaskID");
				String caretaker= rs.getString("Caretaker");	
				boolean completed = rs.getBoolean("Completed");
				Date dateIssued= rs.getDate("DateIssued");
				Date dateDue = rs.getDate("DateDue");
				int timeTaken = rs.getInt("TimeTaken");
				boolean issue = rs.getBoolean("Issue");
				String issueDesc = rs.getString("IssueDesc");
				boolean signedOff = rs.getBoolean("SignedOff");
				Date signedOffOn = rs.getDate("SignedOffOn");
				String taskName = rs.getString("TaskName");
				String taskDesc = rs.getString("TaskDesc");
				String taskCat = rs.getString("TaskCat");
				String priority = rs.getString("Priority");
				int repeating = rs.getInt("Repeating");
				int timeEstimate = rs.getInt("TimeEstimate");
				String location = rs.getString("location");
				Date firstAllocation = rs.getDate("FirstAllocation");
				Date lastAllocated = rs.getDate("LastAllocated");
				//boolean caretakerSignOff = rs.getBoolean("CaretakerSignOff");

				// create new ActiveTask to be added on every loop
				Task taskToAdd;		
					taskToAdd = new Task.TaskBuilder().jobID(jobID).taskID(taskID).taskName(taskName).taskDesc(taskDesc).taskCat(taskCat)
							.priority(priority).repeating(repeating).timeEstimate(timeEstimate).location(location)
							.caretaker(caretaker).completed(completed).dateIssued(dateIssued).dateDue(dateDue)
							.timeEstimate(timeEstimate).timeTaken(timeTaken).issue(issue).issueDesc(issueDesc).signedOff(signedOff)
							.lastAllocated(lastAllocated).firstAllocation(firstAllocation).signedOffOn(signedOffOn)/*.caretakerSignOff(caretakerSignOff)*/
							.build();
				
				// add new task to the list
				allActiveTasks.addTask(taskToAdd);				
			} // while
			return allActiveTasks;
		} // try
		catch (Exception e) 
		{
			// TODO: handle exception
			return null;
		} // catch
	} // function
	
	public Task getTask(int passedTaskID)
	{
			// Query database for a specific task, matching the passed taskID
			Statement stmt;
			Task task = null;
			try 
			{
				stmt = conn.createStatement();
			
				String query = "SELECT JobID, TaskID, Caretaker, DateIssued, DateDue,"
						+ " Completed, TimeTaken, Issue, IssueDesc, SignedOff, signedOffOn,"
						+ " TaskName, TaskDesc, TaskCat, Priority, Repeating, TimeEstimate, Location, "
						+ " FirstAllocation, LastAllocated, TimeGiven, CaretakerSignOff"
						+ " FROM Task"
						+ " LEFT JOIN TaskList ON Task.taskID = TaskList.taskID "
						+ "WHERE Task.taskID = " + passedTaskID;
				
				//Execute the query
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate over data. There should only ever be 1 return
				while (rs.next())
				{
					// assign data to variables
					int jobID = rs.getInt("jobID");
					int taskID = rs.getInt("TaskID");
					String caretaker= rs.getString("Caretaker");	
					boolean completed = rs.getBoolean("Completed");
					Date dateIssued= rs.getDate("DateIssued");
					Date dateDue = rs.getDate("DateDue");
					int timeTaken = rs.getInt("TimeTaken");
					boolean issue = rs.getBoolean("Issue");
					String issueDesc = rs.getString("IssueDesc");
					boolean signedOff = rs.getBoolean("SignedOff");
					Date signedOffOn = rs.getDate("SignedOffOn");
					String taskName = rs.getString("TaskName");
					String taskDesc = rs.getString("TaskDesc");
					String taskCat = rs.getString("TaskCat");
					String priority = rs.getString("Priority");
					int repeating = rs.getInt("Repeating");
					int timeEstimate = rs.getInt("TimeEstimate");
					String location = rs.getString("location");
					Date firstAllocation = rs.getDate("FirstAllocation");
					Date lastAllocated = rs.getDate("LastAllocated");
					boolean caretakerSignOff = rs.getBoolean("CaretakerSignOff");
				
				
						
				task = new Task.TaskBuilder().jobID(jobID).taskID(taskID).taskName(taskName).taskDesc(taskDesc).taskCat(taskCat)
						.priority(priority).repeating(repeating).timeEstimate(timeEstimate).location(location)
						.caretaker(caretaker).completed(completed).dateIssued(dateIssued).dateDue(dateDue)
						.timeEstimate(timeEstimate).timeTaken(timeTaken).issue(issue).issueDesc(issueDesc).signedOff(signedOff)
						.lastAllocated(lastAllocated).firstAllocation(firstAllocation).signedOffOn(signedOffOn).build();
			
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return task;
	}
	
	public void insertTaskList(int taskID, String caretaker, String dateIssued, boolean completed,
			 int timeTaken, boolean issue, String issueDesc, boolean signedOff, String signedOffOn, String dateDue) throws SQLException, ParseException{
		try {
			PreparedStatement sqlInsert = conn.prepareStatement("INSERT INTO TaskList (TaskID, Caretaker, DateIssued,"
					+ "Completed, TimeTaken, Issue, IssueDesc, SignedOff, SignedOffOn, DateDue) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			sqlInsert.setInt(1, taskID);
			sqlInsert.setString(2, caretaker);
			sqlInsert.setDate(3, convertStringToSQLDate(dateIssued));
			sqlInsert.setBoolean(4, completed);
			sqlInsert.setInt(5, timeTaken);
			sqlInsert.setBoolean(6, issue);
			sqlInsert.setString(7, issueDesc);
			sqlInsert.setBoolean(8, signedOff);
			sqlInsert.setDate(9, convertStringToSQLDate(signedOffOn));
			sqlInsert.setDate(10, convertStringToSQLDate(dateDue));
			
			sqlInsert.executeUpdate();
		}
		catch (SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
	}
	
	public void updateLastAllocated(int taskID, String lastAllocated) throws ParseException {
		try {
			PreparedStatement sqlInsert = conn.prepareStatement("UPDATE Task SET LastAllocated = ? WHERE taskID = ?");
			
			sqlInsert.setDate(1, convertStringToSQLDate(lastAllocated));
			sqlInsert.setInt(2, taskID);
			
			sqlInsert.executeUpdate();
		}
		catch (SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
	}
	
	public void removeFromTaskList(int jobID) throws SQLException {
		try {
			PreparedStatement sqlInsert = conn.prepareStatement("DELETE FROM TaskList WHERE jobID = ?");
			
			sqlInsert.setInt(1, jobID);
			
			sqlInsert.executeUpdate();
		}
		catch (SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
	}
	
	public void completeTask(int jobID, int timeTaken) throws SQLException {
		try {
			PreparedStatement sqlInsert = conn.prepareStatement("UPDATE TaskList SET Completed = ?, TimeTaken = ? WHERE jobID = ?");
			
			sqlInsert.setBoolean(1, true);
			sqlInsert.setInt(2, timeTaken);
			sqlInsert.setInt(3, jobID);
			
			sqlInsert.executeUpdate();
		}
		catch (SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
	}
	
	
	public void uncompleteTask(int jobID) throws SQLException {
		try {
			PreparedStatement sqlInsert = conn.prepareStatement("UPDATE TaskList SET Completed = ?, SignedOff = ? WHERE jobID = ?");
			
			System.out.println("Complete task DB" + jobID);
			sqlInsert.setBoolean(1, false);
			sqlInsert.setBoolean(2, false);
			sqlInsert.setInt(3, jobID);
			
			sqlInsert.executeUpdate();
		}
		catch (SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
	}
	
	/**
	 * this function already exists in another class, make interface??
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public java.sql.Date convertStringToSQLDate(String dateString) throws ParseException {
		Date convertedDate;
		  java.sql.Date sConvertedDate;
		  // if the string is not null
		  if(dateString != null) {
			  // convert string
			  convertedDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);  
			  sConvertedDate = new java.sql.Date(convertedDate.getTime());
		  }
		  else {
			  // else converted date is null
			  sConvertedDate = null;
		  }
		  // return converted date
		  return sConvertedDate;
	}
	
	private void displayRow (ResultSet rs, ResultSetMetaData rsmd) 	throws SQLException 
	{
		 for (int i = 1;  i <= rsmd.getColumnCount ();  i ++)
		 switch (rsmd.getColumnType (i)) {
		 case Types.VARCHAR:
		 case Types.LONGVARCHAR:
		 System.out.print (rs.getString (i)+"\t");  break;
		 case Types.INTEGER:
		 case Types.NUMERIC:
		 System.out.print (""+ rs.getInt (i) + "\t");  break;
		 default:	// do nothing � print nothing
		 }
		 System.out.println ();  		
	}
	
	public String getDriver() 
	{
		return driver;
	}
	
	public String getDb() 
	{
		return Db;
	}
	
	public Connection getConn() 
	{
		return conn;
	}
		
	public String getUrl() 
	{
		return url;
	}
		
	public void setDriver(String driver) 
	{
		this.driver = driver;
	}
		
	public void setDb(String db) 
	{
		Db = db;
	}
		
	public void setConn(Connection conn) 
	{
		this.conn = conn;
	}
	
	/**
	 * Takes an insert SQL statement and executes it
	 * 
	 * @param A formatted SQL statement
	 * @return If the statement executed correctly
	 */
	public Boolean executeSQL(String insertSQL)
	{
		try 
		{
			Statement stmt = conn.createStatement();	
			stmt.executeUpdate(insertSQL);
			
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
	}
		
	public void setUrl(String url) 
	{
		this.url = url;
	}
		
	public static void main(String [] args) 
	{
		Database testDb = new Database();
	} 
}
