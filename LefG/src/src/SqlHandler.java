/**
This file is part of LefG.

    LefG is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    LefG is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with LefG.  If not, see <http://www.gnu.org/licenses/>.
**/


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
	public final int QUEUE_ADD = 2;
	public final int QUEUE_UPDATE = 3;
	public final int QUEUE_REMOVE = 4;
	// Our database settings
	private final String DBURL = "jdbc:mysql://db4free.net:3306/swtorlefg";
	private final String DBUN = "swtorlefg";
	private final String DBPW = "lefgpassword";
	
	// jdbc objects
	Connection c = null;
	Statement s = null;
	ResultSet rs = null;
	
	// Initiates connection with the database
	public SqlHandler() {
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
	
	// Closes connection to the database
	public void close() throws SQLException {
		c.close();
	}
	// Inserts a toon into the database
	public void insertToon(Toon t){
		try {
			s.executeUpdate("INSERT INTO Toon (name, class, gear, comment) VALUES ('"+
							t.name+"', '"+t.advclass+"', '"+t.gear+"', '"+t.comment+"');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Gets full list of table Toon in form of ResultSet
	public ResultSet toonList(){
		try {
			rs = s.executeQuery("SELECT * FROM Toon");
			return rs;
			//return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	// Returns the TID in table Toon of the searched string or NOT_FOUND
	public int findName(String name) {
		try {
			rs = s.executeQuery("SELECT TID FROM Toon WHERE name='"+name+"';");
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
	public int findName(String name, String table){
		try {
			rs = s.executeQuery("SELECT TID FROM "+table+" WHERE name='"+name+"';");
			if (!rs.next()){
				return NOT_FOUND;
			}else {
				int TID = rs.getInt("TID");
				return TID;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return NOT_FOUND;
		}
	}
	public boolean findTID(int TID, String table){
		try {
			return(s.executeQuery("SELECT * FROM "+table+" WHERE TID="+TID+";").next());
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			return false;
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
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int updateToonQueue(Toon t){
		try {
			if(!(s.executeQuery("SELECT * FROM tb_queue WHERE TID="+t.TID+";").next())){
				// NEW ENTRY
				s.executeUpdate("INSERT INTO tb_queue (TID, QueueList) VALUES ("+t.TID+", "+t.queues+");");
				return QUEUE_ADD;
			}else if(t.queues > 0){
				s.executeUpdate("UPDATE tb_queue SET QueueList="+t.queues+" WHERE TID="+t.TID+";");
				return QUEUE_UPDATE;
				// UPDATE @ TID
			}else if(t.queues==0 && findTID(t.TID, "tb_queue")){
			   s.executeUpdate("DELETE FROM tb_queue WHERE TID="+t.TID+";");
			   return QUEUE_REMOVE;
			}else{
				return NOT_FOUND;
			}
		} catch (SQLException ex){
			ex.printStackTrace();
			return NOT_FOUND;
		}
	}
}
