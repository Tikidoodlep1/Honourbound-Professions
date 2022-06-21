package com.tiki.honourboundprofessions.helpers;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.tiki.honourboundprofessions.HBP;

public class PersistentDataTracker {

	private static final NamespacedKey levelupkey = new NamespacedKey(HBP.getPlugin(), "ProfessionsLevelups");
	public static final NamespacedKey smithkey = new NamespacedKey(HBP.getPlugin(), "ProfessionsSmithLevels");
	public static final NamespacedKey fletcherkey = new NamespacedKey(HBP.getPlugin(), "ProfessionsFletcherLevels");
	public static final NamespacedKey alchemistkey = new NamespacedKey(HBP.getPlugin(), "ProfessionsAlchemistLevels");
	public static final NamespacedKey farmerkey = new NamespacedKey(HBP.getPlugin(), "ProfessionsFarmerLevels");
	public static final NamespacedKey enchanterkey = new NamespacedKey(HBP.getPlugin(), "ProfessionsEnchanterLevels");
	
	public static int getPlayerLevelups(Player p) {
		PersistentDataContainer data = p.getPersistentDataContainer();
		int levelups = 0;
		if(data.has(levelupkey, PersistentDataType.INTEGER)) {
			levelups = data.get(levelupkey, PersistentDataType.INTEGER);
		}else {
			data.set(levelupkey, PersistentDataType.INTEGER, levelups);
		}
		return levelups;
	}
	
	public static int getLevels(Player p, NamespacedKey key) {
		PersistentDataContainer data = p.getPersistentDataContainer();
		int levels = 0;
		if(data.has(key, PersistentDataType.INTEGER)) {
			levels = data.get(key, PersistentDataType.INTEGER);
		}else {
			data.set(key, PersistentDataType.INTEGER, levels);
		}
		return levels;
	}
	
	public static int modifyPlayerLevelups(Player p, int levelsToAdd, boolean give) {
		PersistentDataContainer data = p.getPersistentDataContainer();
		int levelups = give?getPlayerLevelups(p) + levelsToAdd:getPlayerLevelups(p) - levelsToAdd;
		if(levelups < 0) {
			levelups = 0;
		}
		data.set(levelupkey, PersistentDataType.INTEGER, levelups);
		return levelups;
	}
	
	public static int modifyLevels(Player p, NamespacedKey key, int levelsToAdd, boolean give) {
		PersistentDataContainer data = p.getPersistentDataContainer();
		int levels = give?getLevels(p, key) + levelsToAdd:getLevels(p, key) - levelsToAdd;
		if(levels >= 10) {
			levels = 10;
		}else if(levels < 0) {
			levels = 0;
		}
		data.set(key, PersistentDataType.INTEGER, levels);
		return levels;
	}
}
