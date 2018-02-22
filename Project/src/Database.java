import java.sql.*;

public class Database {
	private String driver = "jdbc:ucanaccess://";
	private String Db = "database//crispyDuckDatabase.accdb";
	private Connection conn = null;
	private String url = driver + Db;
	
	public Database() {
		connect();
		getusers();
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
		    do {
		      displayRow (rs, rsmd); //helper method below
		    }while (rs.next ());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		    finally {
				System.out.println("done");
			}
		  }
		private void displayRow (ResultSet rs, ResultSetMetaData rsmd) 	throws SQLException {
		    for (int i = 1;  i <= rsmd.getColumnCount ();  i ++)
		      switch (rsmd.getColumnType (i)) {
		      case Types.VARCHAR:
		      case Types.LONGVARCHAR:
		        System.out.print (rs.getString (i)+"\t");  break;
		      case Types.INTEGER:
		      case Types.NUMERIC:
		        System.out.print (""+ rs.getInt (i) + "\t");  break;
		      default:	// do nothing – print nothing
		      }
		    System.out.println ();
		  		
	}
	
	public String getDriver() {
			return driver;
		}
		public String getDb() {
			return Db;
		}
		public Connection getConn() {
			return conn;
		}
		public String getUrl() {
			return url;
		}
		public void setDriver(String driver) {
			this.driver = driver;
		}
		public void setDb(String db) {
			Db = db;
		}
		public void setConn(Connection conn) {
			this.conn = conn;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	public static void main(String [] args) {
		Database testDb = new Database();
		} 
}
