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
		We are going to use bitwise operations to keep track of queues
		Recall that 4 binary digits can be represented as 1 hex digit
		I.e. (binary) 1010 1101 1010 1001 in hex 0xADA9
		We'll be representing queues by 1 hex digit, 1 bit per raid type
		
		So, to represent queuing for SNV SM 8 and 16, but not HM we will use the hex
		digit 0xC (in binary, that is the 4 digits:  1    1    0    0
									 				SM8	 SM16 HM8  HM16
		
		Support for NiM raids will be added in future releases
		
		Structure for queues int will be as follows:
		
		EC	EV	KP	TC	SnV	TFB	DP	DF 
**/

package src;
import toon.Toon;
public class QueueHandler {
 
	public int setQueueBit (int bitset, int index){
			return bitset | 1<<index;
	}
	public int unsetQueueBit(int bitset, int index){
		return bitset & ~(1<<index);
	}
	public boolean checkQueueBit(int bitset, int index){
		return ((bitset & (1<<index))==(1<<index));
	}
}
