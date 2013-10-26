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
	/**
		We are going to use bitwise operators to keep track of queues
		Recall that 4 binary digits can be represented as 1 hex digit
		I.e. (binary) 1010 1101 1010 1001 in hex 0xADA9
		Since most queues have SM/HM
	**/
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
