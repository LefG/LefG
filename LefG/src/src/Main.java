package src;
import toon.Toon;
public class Main {
/**
 * ADVANCED CLASSES AS FOLLOWS:
 * ME - Mercenary		TR - Trooper
 * PT - Powertech		VA - Vanguard
 * SN - Sniper			GS - Gunslinger
 * OP - Operative       SC - Scoundrel
 * JG - Juggernaut		GU - Guardian
 * MA - Marauder        SE - Sentinel
 * AS - Assassin		SH - Shadow
 * SO - Sorcerer		SA - Sage
 * 
 */
	public static void main(String[] args) {
		Toon t = new Toon("Grêen", "MA", "78s", "I own you");
		ToonHandler th = new ToonHandler();
		th.addToon(t);
	}

}
