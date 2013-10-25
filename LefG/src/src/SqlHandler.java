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

import toon.Toon;



public class SqlHandler {
	public final int NOT_FOUND = -1;
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
	public void close() throws SQLException {
		c.close();
	}
	public int incrementCTID() {
		int CTID=-1;
		try {
			rs = s.executeQuery("SELECT CTID FROM ctrl");
			rs.first();
			CTID = rs.getInt("CTID");
			updateMaster("CTID", CTID+1);
			return CTID;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return NOT_FOUND;
		}
		
	}
	
	// The next 3 insert methods are currently public, this is for testing purposes only.
	// On any release, these should be set to private to prevent misuse.
	
	public void insertToon(Toon t){
		try {
			s.executeUpdate("INSERT INTO Toon (TID, name, class, gear, comment) VALUES ("+
							t.TID +", '"+t.name+"', '"+t.advclass+"', '"+t.gear+"', '"+t.comment+"');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Insert at beginning of table
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
	public int findName(String v) {
		try {
			rs = s.executeQuery("SELECT TID FROM Toon WHERE name='"+v+"';");
			if (!rs.next()){
				return NOT_FOUND;
			} else {
				int TID = rs.getInt("TID");
				return TID;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return NOT_FOUND;
		}
	}
	public Toon getToonInfo(int TID) {
		Toon t = new Toon();
		try {
			rs = s.executeQuery("SELECT * FROM Toon WHERE TID="+TID+";");
			rs.first();
			t.TID = TID;
			t.name = rs.getString("name");
			t.advclass = rs.getString("class");
			t.gear = rs.getString("gear");
			t.comment = rs.getString("comment");
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void updateMaster(String attr, int v) {
		try {
			s.executeUpdate("UPDATE ctrl SET "+attr+"="+v+" WHERE ID='MASTER';");
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

}
