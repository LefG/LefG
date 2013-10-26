package src;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import toon.Toon;
public class ToonHandler {
	LinkedList<Toon> Toons = new LinkedList<Toon>();
	SqlHandler sh = new SqlHandler();
	Toon t;
	
	// Get info on toons already on db
	public ToonHandler(){
		File file = new File("toonlist.ini");
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
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
	public void addToon(Toon t) {
		int TID = sh.findName(t.name);
		if(TID > sh.NOT_FOUND){
			t.TID = TID;
			Toons.add(t);
		} else if(TID == sh.NOT_FOUND) {
			sh.insertToon(t);
			Toons.add(t);
		} else System.out.println("Hello Alice. You've arrived a little early");
	}
}
