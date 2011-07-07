package nsdb.db.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import nsdb.cytoscape.Settings;


public class ConnectSQLite {
	
	/** connect to SQLite database defined in settings*/
	public static void testConnection(){
		System.out.println("\ntestConnection()");
		Connection connection = null;
		Statement statement = null;

		try{
			System.out.println("Connect to: " + Settings.SQLITE_DB);
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(Settings.SQLITE_DB);
			statement = connection.createStatement();
		}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				statement.close();
				connection.close();
			}
			catch (Exception e)
				{ e.printStackTrace();}
		}
	}
	
	/** Read data from table */
	public static void getDataFromTable(String table){
		System.out.println("getDataFromTable");
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;

		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(Settings.SQLITE_DB);
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("SELECT * FROM " + table);
			System.out.println(resultSet);
			while (resultSet.next()){
				System.out.println(resultSet.getInt("id"));
			}
		}
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			}
			catch (Exception e)
				{ e.printStackTrace();}

		}
		// Return the data
	}
	
	
	/**
	 * Test the connection to the SQLite Database.
	 * Set the file to 
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectSQLite.testConnection();
		ConnectSQLite.getDataFromTable("xref");
	}

}
