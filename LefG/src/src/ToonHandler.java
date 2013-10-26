package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.LinkedList;

import toon.Toon;
public class ToonHandler {
	File toonfile = new File("toonlist.ini");
	public LinkedList<Toon> Toons = new LinkedList<Toon>();
	SqlHandler sh = new SqlHandler();
	Toon t;
	
	public final int E_ADDTOON = -2; //Error return from addToon()
	public final int FROM_DB = 2;	 //New db entry
	public final int FROM_LOCAL=3;	 //Already in db
	// Get info on toons already on db
	public ToonHandler(){
		// Look up usernames locallly stored on db
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
		int TID = sh.findName(t.name);
		if(TID > sh.NOT_FOUND){
			t.TID = TID;
			Toons.add(t);
			return FROM_LOCAL;
		} else if(TID == sh.NOT_FOUND) {
			sh.insertToon(t);
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
	
	// Checks to see if toon is in local list
	public boolean hasToonLocal(String name) {
		for(int i=0;i<Toons.size(); i++){
			if(Toons.get(i).name.equals(name)){
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
}
