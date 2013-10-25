/**
 * @author: Jeffrey Moon
 * @date: Oct 24, 2013
 * @description: This is a mysql handler for the LefG Group finder enhancement. This handler 
 * 				 utilizes the jdbc library to easily process all requests.
 */
package src;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SqlHandler {
	// Our database settings
	private final String DBURL = "jdbc:mysql://db4free.net:3306/swtorlefg";
	private final String DBUN = "swtorlefg";
	private final String DBPW = "lefgpassword";
	
	// jdbc objects
	Connection c = null;
	Statement s = null;
	ResultSet rs = null;
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			c = DriverManager.getConnection(DBURL, DBUN, DBPW);
			s = c.createStatement();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


 
   
	
	
	public int addingToon() {
		int CTID=-1;
		try {
			rs = s.executeQuery("SELECT CTID FROM ctrl");
			rs.first();
			CTID = rs.getInt("CTID");
			updateMaster("CTID", CTID+1);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return CTID;
	}
	
	// The next 3 insert methods are currently public, this is for testing purposes only.
	// On any release, these should be set to private to prevent misuse.
	
	
	// Insert at beginning of table
	// UPDATE table SET attr=int WHERE set=
	public void updateMaster(String attr, int v) {
		try {
			s.executeUpdate("UPDATE ctrl SET "+attr+"="+v+" WHERE ID='MASTER';");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	public void insert(String table, String attr, int v) {
		try {
			s.executeUpdate("INSERT INTO " +table+" ("+attr+") VALUES ("+v+");");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void insert(String table, String v){
		try {
			s.executeUpdate("INSERT INTO "+table+" VALUES ('"+v+"');");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	// Insert at specific attribute
	public void insert(String table, String attr, String v) {
		try {
			s.executeUpdate("INSERT INTO "+table+" ("+attr+") VALUES ('"+v+"')");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	// Send raw sql syntax, can be used for INSERT, UPDATE, etc
	public void sendUpdate(String sqlCmd){
		try {
			s.executeUpdate(sqlCmd);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
