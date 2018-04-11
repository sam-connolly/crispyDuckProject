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
	
	public boolean deleteEntry(String username) throws SQLException{
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
	
	public void getusers() {
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT UserID, EmployeeName FROM User";
		    ResultSet rs = stmt.executeQuery (query);
		    
		    // position to first record
		    boolean moreRecords = rs.next ();
		    // If there are no records, display a message
		    if (!moreRecords) {
		      System.out.println ("ResultSet contained no records");
		      return;
		    }
		    // display column headings
		    ResultSetMetaData rsmd = rs.getMetaData ();
		    for (int i = 1;  i <= rsmd.getColumnCount ();  i ++)
		      System.out.print (rsmd.getColumnName (i) + "\t");
		    System.out.println ();
		    // display rows of data ....
		    do 
		    {
		      displayRow (rs, rsmd); //helper method below
		    }
		    while (rs.next ());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		    finally {
				System.out.println("done");
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
			String query = "SELECT Username, fName, sName FROM User WHERE admin = 'false'";
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
	 *  function to retrieve data from TaskList and add it to a collection
	 *  of ActiveTask objects
	 */
	public ActiveTaskList getActiveTasks()
	{
		try {
			// query database for data in TaskList
			Statement stmt = conn.createStatement();
			String query = "SELECT TaskID, Caretaker, DateIssued, DateDue,"
					+ " Completed, TimeTaken, IssueDesc, SignedOff,"
					+ " CompletedOn, TaskName, TaskDesc, Priority, TimeEstimate, Location"
					+ " FROM TaskList"
					+ " LEFT OUTER JOIN Task ON JobID = JobID";
			ResultSet rs = stmt.executeQuery(query);
			
			// create a new ActiveTaskList object to store data from results set
			ActiveTaskList allActiveTasks = new ActiveTaskList();
			
			// iterate over data
			while (rs.next())
			{
				// assign data to variables
				int taskID = rs.getInt("TaskID");
				String caretaker= rs.getString("Caretaker");	
				boolean completed = rs.getBoolean("Completed");
				Date completedOn = rs.getDate("CompletedOn");
				Date dateIssued= rs.getDate("DateIssued");
				Date dateDue = rs.getDate("DateDue");
				int timeTaken = rs.getInt("TimeTaken");
				String issueDesc = rs.getString("IssueDesc");
				boolean signedOff = rs.getBoolean("SignedOff");
				String taskName = rs.getString("TaskName");
				String taskDesc = rs.getString("TaskDesc");
				String priority = rs.getString("Priority");
				int timeEstimate = rs.getInt("TimeEstimate");
				String location = rs.getString("location");
				
				// create new ActiveTask to be added on every loop
				ActiveTask taskToAdd = new ActiveTask(taskID, caretaker, completed, completedOn, dateIssued, dateDue,
						timeTaken, 
						issueDesc, signedOff, taskName, taskDesc, priority,
						timeEstimate, location);
				
				// add new task to the list
				allActiveTasks.addTask(taskToAdd);
				
			}
			
			return allActiveTasks;
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			return null;
		}
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
