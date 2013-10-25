package src;
import java.util.LinkedList;

import toon.Toon;
public class ToonHandler {
	LinkedList<Toon> list = new LinkedList<Toon>();
	SqlHandler sh = new SqlHandler();
	Toon t;
	public ToonHandler(){
		sh.connect();
	}
	
	// Add a toon to the local linked list. Adds to global toon list on db when name not found
	public void addToon(Toon t) {
		int TID = sh.findName(t.name);
		if(TID > sh.NOT_FOUND){
			t.TID = TID;
			list.add(t);
		} else if(TID == -1) {
			t.TID = sh.incrementCTID();
			sh.insertToon(t);
			list.add(t);
		}
		for(int i=0; i<list.size(); i++){
			t = list.get(i);
			System.out.printf("%d\t%s\t%s\t%s\t%s\n", t.TID, t.name, t.advclass, t.gear, t.comment);
		}
	}

}
