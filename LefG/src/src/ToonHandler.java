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


package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import toon.Toon;
public class ToonHandler {
	File toonfile = new File("toonlist.ini");
	public LinkedList<Toon> Toons = new LinkedList<Toon>();
	SqlHandler sh = new SqlHandler();
	QueueHandler q = new QueueHandler();
	Toon t;

	
	public final int E_ADDTOON_LOCAL = -3;
	public final int E_ADDTOON = -2; //Error return from addToon()
	public final int FROM_DB = 2;	 //New db entry
	public final int FROM_LOCAL=3;	 //Already in db
	// Get info on toons already on db
	public ToonHandler(){
		// Look up usernames locally stored on db
		try {
			BufferedReader in = new BufferedReader(new FileReader(toonfile));
			in.readLine(); //Get past first line of file
			String name=in.readLine();
			
			while(name!=null){
				int TID = sh.findName(name);
				if(TID>sh.NOT_FOUND){
					t=sh.getToonInfo(TID);
					Toons.add(t);
				}else{
					System.out.println("Error looking up username");
				}
			name=in.readLine();
			}
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	// Add a toon to the local linked list. Adds to global toon list on db when name not found
	public int addToon(Toon t) {
		// First, we must make sure the toon is is not a duplicate on the local list
		if(hasToonLocal(t.name)){
			System.out.println(t.name);
			return E_ADDTOON_LOCAL;
		}else{
			int TID = sh.findName(t.name);
			if(TID > sh.NOT_FOUND){
				t.TID = TID;
				Toons.add(t);
				return FROM_LOCAL;
			} else if(TID == sh.NOT_FOUND) {
				sh.insertToon(t);
				t.TID = sh.findName(t.name);
				try {
					PrintWriter out = new PrintWriter(new FileWriter(toonfile, true));
					out.println(t.name);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Toons.add(t);
				return FROM_DB;
			} else {
				System.out.println("Hello Alice. You've arrived a little early");
				return E_ADDTOON;
			}
		}
	}
	
	// Checks to see if toon is in local list
	public boolean hasToonLocal(String name) {
		for(int i=0;i<Toons.size(); i++){
			if((Toons.get(i).name).equals(name)){
				return true;
			}
		}
		return false;
	}
	
	// Checks to see if toon is on db
	public boolean hasToonDb(String name) {
		if(sh.findName(name) > sh.NOT_FOUND) return true;
		return false;
	}

	// For easily accessing a specific bit
	// For instance use (KP+SM16) to access the KPSM16 queue status
	

	public void queueToon(Toon t, int op){
		t.queues = q.setQueueBit(t.queues, op);
	}
	public void dequeueToon(Toon t, int op){
		t.queues = q.unsetQueueBit(t.queues, op);
	}
	
	public void commitQueue(Toon t){
		int r = sh.addToonQueue(t);
		if (r == sh.QUEUE_ADD){
			System.out.println("Added to queue");
		}else if(r==sh.QUEUE_UPDATE){
			System.out.println("Updated queue");
		}
	}
}
