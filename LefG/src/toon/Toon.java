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
package toon;

public class Toon {
	public int TID=-1;
	public String name;
	public String advclass;
	public String gear;
	public int faction;
	public String comment;
	public String server;
	public int queues = 0x0; //Further explanation on the queue functions can be found in the class QueueHandler.java
	
	
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
