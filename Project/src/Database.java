import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Database {
	private String driver = "jdbc:ucanaccess://";
	private String Db = "database//crispyDuckDatabase.accdb";
	private Connection conn = null;
	private String url = driver + Db;
	private String user, password;
	
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
	
	@SuppressWarnings("finally")
	public boolean validateLogin(String user,  String password) {
		try {
			this.user = user;
			this.password = password;
			Statement stmt = conn.createStatement();
			String query = "SELECT PasswordHash FROM User WHERE Username=user";
			ResultSet rs = stmt.executeQuery(query);
			boolean moreRecords = rs.next();
		    if (!moreRecords) {
			      System.out.println ("ResultSet contained no records");
			      return false;
		    }
			ResultSetMetaData rsmd = rs.getMetaData();
			if (password==rsmd.getColumnName(1)) {
				return true;
			}
		}
		catch(Exception e){
			
		}
		finally {
			return false;
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
