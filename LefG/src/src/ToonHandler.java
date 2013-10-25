package src;
public class ToonHandler {
	SqlHandler sh = new SqlHandler();
	public void addToon(String name, String advclass, String gear, String comment) {
		sh.connect();
		sh.addingToon();
		//sh.insert("Toon", "name", name);
		//sh.insert("Toon", "class", advclass);
	}
}
