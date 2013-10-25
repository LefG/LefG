package toon;

public class Toon {
	public int TID=-1;
	public String name;
	public String advclass;
	public String gear;
	public String comment;
	public String server;
	public Toon(String name, String advclass, String gear, String comment) {
		this.name = name;
		this.advclass = advclass;
		this.gear = gear;
		this.comment = comment;
	}
	public Toon(){
		this.name = null;
		this.advclass = null;
		this.gear = null;
		this.comment = null;
	}
}
