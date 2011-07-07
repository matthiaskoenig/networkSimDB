package nsdb.db.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HepatoCoreInfo {
	private String info;
	
	public HepatoCoreInfo(String id){
		loadInfo(id);
		printInfo();
	}
	
	/** Load the information for the given identifier from the HepatoCore Database */
	public void loadInfo(String id){
		System.out.println("Load HepatoCore information: " + id);
		info = null;
		
		String table = "events_networkobject";
		Connection c = null;
		
		// Create the connection to the database
		try {
			  Class.forName("org.postgresql.Driver");
			  try {
				//TODO: general settings for the database to connect to
			    c = DriverManager.getConnection("jdbc:postgresql://localhost/HepatoCore1",
			                                    "mkoenig", "xo3Quili");
			  } catch (SQLException se) {
			    se.printStackTrace();
			  }
			} catch (ClassNotFoundException cnfe) {
			  System.err.println("Couldn't find driver class:");
			  cnfe.printStackTrace();
		}
		
			// Load the information from the database for the given id
			Statement s = null;
			try {
			  s = c.createStatement();
			} catch (SQLException se) {
			  System.out.println("We got an exception while creating a statement:" +
			                     "that probably means we're no longer connected.");
			  se.printStackTrace();
			}
			ResultSet rs = null;
			try {
			  String query = "SELECT * FROM " + table + " WHERE id=" + id;
			  System.out.println("QUERY:\t" + query);
			  rs = s.executeQuery(query);
			  // move resultset to the first row
			  if (rs.next() == true){
				  info = "id: " + rs.getInt("id") + "\n" +
				  		 "name: " + rs.getString("name") + "\n" +
				  		 "object_type_id: " + rs.getInt("object_type_id") + "\n";
			  }
			  else {
				  info = "no db information available";
			  }
			  			  
			} catch (SQLException se) {
			  System.out.println("We got an exception while executing our query:" +
			                     "that probably means our SQL is invalid");
			  se.printStackTrace();
			}
	}
	
	
	/**
	 * Print the information to the Desktop
	 */
	public void printInfo(){
		System.out.println(info);
	}
	
	/**
	 * Create HTML information string 
	 */
	public String createHTMLInfo(){
		return info;
	}
}
