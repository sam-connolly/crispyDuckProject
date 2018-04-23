import java.sql.*;
import java.util.*;
import java.util.Date;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}	
	
	public boolean validateLogin(String user,  String password) {
		boolean validLogin = false;
		try {
			System.out.println("Attempting login: " + user + ", " + password);
			PreparedStatement stmt = conn.prepareStatement("SELECT Username, PasswordHash FROM User");
			System.out.println("1");
			ResultSet rs = stmt.executeQuery();
			System.out.println("2");
			boolean moreRecords = rs.next();
			System.out.println("More records: " + moreRecords);
			//If there are no records to show validLogin is set to false
		    if (!moreRecords) {
			      System.out.println ("ResultSet contained no records");
			      return false;
		    }
			ResultSetMetaData rsmd = rs.getMetaData();
			//If the entered password matches the one stored in the database
			//validLogin is set to true
			while (rs.next()) {
				System.out.println(password);
				System.out.println(rs.getString("Username") + rs.getString("PasswordHash"));
				if ((password.equals(rs.getString("PasswordHash")))&&(user.equals(rs.getString("Username")))) {
					validLogin=true;
					System.out.println("Sucess");
					break;
				}
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
			PreparedStatement stmt = conn.prepareStatement("Select Username, Admin FROM User");
			ResultSet rs = stmt.executeQuery();
			boolean moreRecords = rs.next();
			if(!moreRecords) {
				System.out.println("Result set contained no records");
				return false;
			}
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				System.out.println(rs.getString("Username") + " is admin? " + rs.getBoolean("Admin"));
				Boolean result = rs.getBoolean("Admin");
				if ((user.equals(rs.getString("Username"))&&(result==true))) {
					isAdmin=true;
				}
			}
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
	
	public boolean deleteUser(String username) throws SQLException{
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
	
	public boolean updateUser(String username, String password, String fName, String sName) throws SQLException{
		PreparedStatement sqlUpdate = null;
		try {
			sqlUpdate = conn.prepareStatement("UPDATE User SET (PasswordHash, fName, sName) VALUES (?,?,?,?) WHERE Username = ?");
		}
		catch(SQLException sqlex) {
			System.err.println("SQL Exception");
			sqlex.printStackTrace();
		}
		sqlUpdate.setString(1, password);
		sqlUpdate.setBoolean(2, admin);
		sqlUpdate.setString(3, forename);
		sqlUpdate.setString(4, surname);
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
			sqlUpdate = conn.prepareStatement("UPDATE User SET (PasswordHash) VALUES (?) WHERE Username = ?");
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
		try {
			// query database for data in TaskList
			Statement stmt = conn.createStatement();
			String query = "SELECT TaskID, Caretaker, DateIssued, DateDue,"
					+ " Completed, TimeTaken, IssueDesc, SignedOff, CompletedOn,"
					+ " TaskName, TaskDesc, TaskCat, Priority, Repeating, TimeEstimate, Location"
					+ " FROM Task"
					+ " LEFT JOIN TaskList ON Task.taskID = TaskList.taskID";
			ResultSet rs = stmt.executeQuery(query);
			
			// create a new ActiveTaskList object to store data from results set
			TaskList allActiveTasks = new TaskList();
			
			// iterate over data
			while (rs.next())
			{
				// assign data to variables
				int taskID = rs.getInt("TaskID");
				String caretaker= rs.getString("Caretaker");	
				boolean completed = rs.getBoolean("Completed");
				Date completedOn = rs.getDate("CompletedOn");
				String dateIssued= rs.getString("DateIssued");
				Date dateDue = rs.getDate("DateDue");
				int timeTaken = rs.getInt("TimeTaken");
				String issueDesc = rs.getString("IssueDesc");
				boolean signedOff = rs.getBoolean("SignedOff");
				String taskName = rs.getString("TaskName");
				String taskDesc = rs.getString("TaskDesc");
				String taskCat = rs.getString("TaskCat");
				String priority = rs.getString("Priority");
				int repeating = rs.getInt("Repeating");
				int timeEstimate = rs.getInt("TimeEstimate");
				String location = rs.getString("location");
				
				// create new ActiveTask to be added on every loop
				Task taskToAdd;		
					taskToAdd = new Task.TaskBuilder().taskID(taskID).taskName(taskName).taskDesc(taskDesc).taskCat(taskCat)
							.priority(priority).repeating(repeating).timeEstimate(timeEstimate).location(location)
							.caretaker(caretaker).completed(completed).completedOn(completedOn).dateIssued(dateIssued).dateDue(dateDue)
							.timeEstimate(timeEstimate).timeTaken(timeTaken).issueDesc(issueDesc).signedOff(signedOff).build();
				
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
	
	public Boolean createTask(String insertSQL)
	{
		try 
		{
			Statement stmt = conn.createStatement();	
			stmt.executeUpdate(insertSQL);
			
			return true;
		}
		catch (Exception e) 
		{
			// TODO: handle exception
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
